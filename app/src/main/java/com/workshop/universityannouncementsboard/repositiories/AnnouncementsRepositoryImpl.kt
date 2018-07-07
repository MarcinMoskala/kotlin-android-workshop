package com.workshop.universityannouncementsboard.repositiories

import com.workshop.universityannouncementsboard.model.Announcement
import com.workshop.universityannouncementsboard.model.Response
import com.workshop.universityannouncementsboard.model.Success

class AnnouncementsRepositoryImpl(val studentsRepository: StudentsRepository) : AnnouncementsRepository {

    override fun getAnnouncements(): Response<List<Announcement>, Throwable> {
        Thread.sleep(1000)
        return Success(listOf(
                Announcement(
                        "Students who are passing:", // TODO: Replace with: span { +"Students who are "; color(Color.GREEN) { +"passing" } }
                        makePassingStudentsListText()
                ),
                Announcement(
                        "Internships are going to", // TODO: Replace with: span { bold { +"Internships" } ; +" are going to" }
                        makeBestStudentsList()
                )
        ))
    }

    // TODO: Should return passing students list. See PassingStudentsListTest
    fun makePassingStudentsListText(): String = ""

    // TODO: Should return students for internship. See BestStudentsListTest
    fun makeBestStudentsList(): String = ""
}