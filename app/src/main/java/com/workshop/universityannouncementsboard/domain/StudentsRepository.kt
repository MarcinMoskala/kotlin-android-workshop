package com.workshop.universityannouncementsboard.domain

import com.workshop.universityannouncementsboard.model.Student

interface StudentsRepository {
    suspend fun getStudents(): List<Student>
}