package com.workshop.universityannouncementsboard.repositiories

import com.workshop.universityannouncementsboard.model.Student

interface StudentsRepository {
    fun getStudents(): List<Student>
}