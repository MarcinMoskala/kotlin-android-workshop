package com.workshop.universityannouncementsboard.repositiories

import com.workshop.universityannouncementsboard.AnnouncementsList
import com.workshop.universityannouncementsboard.model.Announcement
import com.workshop.universityannouncementsboard.model.ErrorResponse
import com.workshop.universityannouncementsboard.model.Response
import com.workshop.universityannouncementsboard.model.Success
import java.util.*

class AnnouncementsRepositoryImpl(val studentsRepository: StudentsRepository) : AnnouncementsRepository {

    override fun getAnnouncements(): Response<List<Announcement>, Throwable> {
        Thread.sleep(2000) // Nobody trust university system that works too fast
        if (Random().nextBoolean()) return ErrorResponse(Error("Random error")) // Nobody trust university system that is fully reliable
        val announcements = AnnouncementsList.getAnnouncements(
                passingStudentsListText = makePassingStudentsListText(),
                bestStudentsListText = makePassingStudentsListText()
        )
        return Success(announcements)
    }

    fun makePassingStudentsListText(): String = studentsRepository
            .getStudents()
            .filter { it.pointsInSemester > 15 && it.result >= 50 }
            .sortedWith(compareBy({ it.surname }, { it.name }))
            .joinToString(separator = "\n") { "${it.name} ${it.surname}, ${it.result}" }

    fun makeBestStudentsList(): String = studentsRepository.getStudents()
            .filter { it.pointsInSemester >= 30 && it.result >= 80 }
            .sortedByDescending { it.result }
            .zip(internships)
            .sortedWith(compareBy({ it.first.surname }, { it.first.name }))
            .joinToString(separator = "\n") { (s, i) -> "${s.name} ${s.surname}, $i\$" }

    private val internships = List(1) { 5000 } + List(3) { 3000 } + List(6) { 1000 }
}