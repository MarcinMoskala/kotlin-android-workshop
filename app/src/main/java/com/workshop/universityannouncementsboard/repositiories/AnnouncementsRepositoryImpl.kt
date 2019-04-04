package com.workshop.universityannouncementsboard.repositiories

import com.workshop.universityannouncementsboard.AnnouncementsList
import com.workshop.universityannouncementsboard.model.Announcement
import com.workshop.universityannouncementsboard.model.ErrorResponse
import com.workshop.universityannouncementsboard.model.Response
import com.workshop.universityannouncementsboard.model.Success
import java.util.*
import kotlin.concurrent.*

class AnnouncementsRepositoryImpl(val studentsRepository: StudentsRepository) : AnnouncementsRepository {

    override fun getAnnouncements(callback: (Response<List<Announcement>, Throwable>)->Unit) {
        thread {
            Thread.sleep(2000) // Nobody trust university system that works too fast
            if (Random().nextBoolean()) {
                callback(ErrorResponse(Error("Random error"))) // Nobody trust university system that is fully reliable
            } else {
                val announcements = AnnouncementsList.getAnnouncements(
                    passingStudentsListText = makePassingStudentsListText(),
                    bestStudentsListText = makePassingStudentsListText()
                )
                callback(Success(announcements))
            }
        }
    }

    // TODO: Should return passing students list. See PassingStudentsListTest
    fun makePassingStudentsListText(): String = "" // Get students suing studentsRepository.getStudents()


    // TODO: Should return students for internship. See BestStudentsListTest
    fun makeBestStudentsList(): String = "" // Get students suing studentsRepository.getStudents()
}