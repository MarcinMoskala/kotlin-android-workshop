package com.workshop.universityannouncementsboard.repositiories

import com.workshop.universityannouncementsboard.*
import com.workshop.universityannouncementsboard.model.*
import com.workshop.universityannouncementsboard.util.*
import java.util.*
import kotlin.concurrent.*

class AnnouncementsRepositoryImpl(val studentsRepository: StudentsRepository) : AnnouncementsRepository {

    override fun getAnnouncements(callback: (Response<List<Announcement>, Throwable>) -> Unit) {
        thread {
            Thread.sleep(2000) // Nobody trust university system that works too fast
            val ret: Response<List<Announcement>, Throwable> = if (Random().nextBoolean()) {
                ErrorResponse(Error("Random error")) // Nobody trust university system that is fully reliable
            } else {
                val announcements = AnnouncementsList.getAnnouncements(
                    passingStudentsListText = makePassingStudentsListText(),
                    bestStudentsListText = makePassingStudentsListText()
                )
                Success(announcements)
            }
            uiThread { callback(ret) }
        }
    }

    // TODO: Should return passing students list. See PassingStudentsListTest
    fun makePassingStudentsListText(): String = studentsRepository.getStudents()
        .filter { it.result >= 50 && it.pointsInSemester > 15 }
        .sortedWith(compareBy({ it.surname }, { it.name }))
        .joinToString(separator = "\n") { "${it.name} ${it.surname}, ${it.result}" }

    val rewards = List(1) { 5000 } + List(3) { 3000 } + List(6) { 1000 }

    fun makeBestStudentsList(): String = studentsRepository.getStudents()
        .filter { it.result >= 80 && it.pointsInSemester >= 30 }
        .sortedByDescending { it.result }
        .zip(rewards)
        .sortedWith(compareBy({ it.first.surname }, { it.first.surname }))
        .joinToString(separator = "\n") { (student, reward) ->
            "${student.name} ${student.surname}, \$$reward"
        }
}