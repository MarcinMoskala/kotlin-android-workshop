package com.workshop.universityannouncementsboard.repositiories

import com.workshop.universityannouncementsboard.domain.StudentsRepository
import com.workshop.universityannouncementsboard.model.Student
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

class NetworkStudentRepository(
        private val api: Api = makeRetrofit("https://api.kt.academy/api/").create(Api::class.java)
): StudentsRepository {

    override suspend fun getStudents(): List<Student> {
        TODO("Not yet implemented")
    }

    interface Api {

        @Headers("Accept: application/json")
        @GET("v1/student")
        suspend fun getStudents(): List<StudentJson>

        @Headers("Accept: application/json")
        @GET("v2/semester/keys")
        suspend fun getSemesterKeys(): List<String>

        @Headers("Accept: application/json")
        @GET("v2/semester/{key}")
        suspend fun getSemester(@Path("key") semesterKey: String): List<SemesterJson>
    }
}

class StudentJson(
        val name: String,
        val surname: String,
        val result: Double,
        val pointsInSemester: Int
)

fun StudentJson.toStudent() = Student(
        name = name,
        surname = surname,
        result = result,
        pointsInSemester = pointsInSemester
)

class SemesterJson(
        val key: String,
        val students: List<Student>
)