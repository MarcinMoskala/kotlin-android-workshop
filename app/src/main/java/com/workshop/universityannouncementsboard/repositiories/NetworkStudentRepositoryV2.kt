package com.workshop.universityannouncementsboard.repositiories

import com.workshop.universityannouncementsboard.domain.StudentsRepository
import com.workshop.universityannouncementsboard.model.Student
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

class NetworkStudentRepositoryV2(
    private val api: Api = makeRetrofit("https://api.kt.academy/api/").create(Api::class.java)
) : StudentsRepository {

    override suspend fun getStudents(): List<Student> = TODO()

    interface Api {
        @Headers("Accept: application/json")
        @GET("v2/semester")
        suspend fun getSemesterKeys(): List<String>

        @Headers("Accept: application/json")
        @GET("v2/semester/{key}")
        suspend fun getSemester(@Path("key") semesterKey: String): SemesterJsonV2
    }
}

class StudentJsonV2(
    val name: String,
    val surname: String,
    val result: Double,
    val pointsInSemester: Int
)

fun StudentJsonV2.toStudent() = Student(
    name = name,
    surname = surname,
    result = result,
    pointsInSemester = pointsInSemester
)

class SemesterJsonV2(
    val key: String,
    val students: List<StudentJsonV2>
)