package com.workshop.universityannouncementsboard.repositiories

import com.workshop.universityannouncementsboard.domain.StudentsRepository
import com.workshop.universityannouncementsboard.model.Student
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

class NetworkStudentRepositoryV1(
        private val api: Api = makeRetrofit("https://api.kt.academy/api/").create(Api::class.java)
) : StudentsRepository {

    override suspend fun getStudents(): List<Student> = TODO()
    interface Api {

        @Headers("Accept: application/json")
        @GET("v1/student")
        suspend fun getStudents(): List<StudentJsonV1>
    }
}

class StudentJsonV1(
        val name: String,
        val surname: String,
        val result: Double,
        val pointsInSemester: Int
)