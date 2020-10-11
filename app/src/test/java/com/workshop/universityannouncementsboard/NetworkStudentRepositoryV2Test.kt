package com.workshop.universityannouncementsboard

import com.workshop.universityannouncementsboard.model.Student
import com.workshop.universityannouncementsboard.repositiories.NetworkStudentRepositoryV2
import com.workshop.universityannouncementsboard.repositiories.SemesterJsonV2
import com.workshop.universityannouncementsboard.repositiories.StudentJsonV2
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.test.assertEquals

class NetworkStudentRepositoryV2Test {

    @Test
    fun `Function does return all the students`() = runBlockingTest {
        // given
        val apiFake = FakeStudentApi()
        val repo = NetworkStudentRepositoryV2(apiFake)

        // when
        val res = repo.getStudents()

        // then
        val expected = listOf(
                aStudent1,
                aStudent2,
                aStudent3,
                aStudent4,
        )
        assertEquals(expected, res)
    }

    @Test
    fun `Requests do not wait for each other`() = runBlockingTest {
        val apiFake = WaitingFakeStudentApi()
        val repo = NetworkStudentRepositoryV2(apiFake)

        val before = currentTime
        repo.getStudents()
        val after = currentTime
        kotlin.test.assertEquals(12_000, after - before)
    }

    @Test
    fun `Cancellation works fine`() = runBlockingTest {
        val apiFake = WaitingFakeStudentApi()
        val repo = NetworkStudentRepositoryV2(apiFake)

        val job = launch {
            val chosen = repo.getStudents()
        }
        delay(300)
        job.cancel()
        delay(100000)
        assertEquals(0, apiFake.returnedStudents)
    }

    @Test
    fun `When one request has error, all are stopped and error is thrown`() = runBlockingTest {
        val apiFake = FirstFailingFakeStudentApi()
        val repo = NetworkStudentRepositoryV2(apiFake)

        assertThrowsError<FirstFailingFakeStudentApi.FirstFailingError> {
            repo.getStudents()
        }
        delay(1000)
        assertEquals(0, apiFake.studentsReturned, "Looks like some requests were still running after the first one had an error")
    }

    inline fun <reified T : Throwable> assertThrowsError(body: () -> Unit) {
        try {
            body()
            assert(false) { "There should be an error of type ${T::class.simpleName}" }
        } catch (throwable: Throwable) {
            if (throwable !is T) {
                throw throwable
            }
        }
    }

    class FakeStudentApi : NetworkStudentRepositoryV2.Api {
        override suspend fun getSemesterKeys(): List<String> = listOf("S1", "S2", "S3")

        override suspend fun getSemester(semesterKey: String): SemesterJsonV2 = SemesterJsonV2(
                key = semesterKey,
                students = when (semesterKey) {
                    "S1" -> listOf(aStudent1.toStudentJson())
                    "S2" -> listOf(aStudent2.toStudentJson(), aStudent3.toStudentJson())
                    "S3" -> listOf(aStudent4.toStudentJson())
                    else -> error("Unknown semester $semesterKey")
                })

    }

    class WaitingFakeStudentApi : NetworkStudentRepositoryV2.Api {
        var returnedStudents = 0

        override suspend fun getSemesterKeys(): List<String> {
            delay(2000)
            return (1..5).map { "S$it" }
        }

        override suspend fun getSemester(semesterKey: String): SemesterJsonV2 {
            delay(10000)
            returnedStudents++
            return SemesterJsonV2(semesterKey, listOf(aStudent1.toStudentJson()))
        }
    }

    class FirstFailingFakeStudentApi : NetworkStudentRepositoryV2.Api {
        var studentsReturned = 0
        private var first = true
        private val mutex = Mutex()

        override suspend fun getSemesterKeys(): List<String> {
            delay(2000)
            return (1..5).map { "S$it" }
        }

        override suspend fun getSemester(semesterKey: String): SemesterJsonV2 {
            delay(100)
            mutex.withLock {
                // To prevent more than one throwing
                if (first) {
                    first = false
                    throw FirstFailingError()
                }
            }
            delay(100)
            studentsReturned++
            return SemesterJsonV2(semesterKey, listOf(aStudent1.toStudentJson()))
        }

        class FirstFailingError() : Error()
    }
}

private fun Student.toStudentJson() = StudentJsonV2(
        name = name,
        surname = surname,
        result = result,
        pointsInSemester = pointsInSemester
)