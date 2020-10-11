package com.workshop.universityannouncementsboard

import com.workshop.universityannouncementsboard.model.Student
import com.workshop.universityannouncementsboard.repositiories.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.test.assertEquals

class NetworkStudentRepositoryV1Test {

    @Test
    fun `Function does return all the students`() = runBlockingTest {
        // given
        val students = listOf(aStudent1, aStudent2, aStudent3, aStudent4)
        val apiFake = FakeStudentApi(students)
        val repo = NetworkStudentRepositoryV1(apiFake)

        // when
        val res = repo.getStudents()

        // then
        assertEquals(students, res)
    }

    class FakeStudentApi(val students: List<Student>) : NetworkStudentRepositoryV1.Api {

        override suspend fun getStudents(): List<StudentJsonV1> =
                students.map { it.toStudentJson() }
    }
}

private fun Student.toStudentJson() = StudentJsonV1(
        name = name,
        surname = surname,
        result = result,
        pointsInSemester = pointsInSemester
)