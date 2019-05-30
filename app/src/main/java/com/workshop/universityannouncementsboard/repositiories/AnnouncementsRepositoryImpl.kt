package com.workshop.universityannouncementsboard.repositiories

import com.workshop.universityannouncementsboard.*
import com.workshop.universityannouncementsboard.model.*
import com.workshop.universityannouncementsboard.util.*
import kotlin.concurrent.*
import kotlin.random.*

class AnnouncementsRepositoryImpl(val studentsRepository: StudentsRepository) : AnnouncementsRepository {

    override fun getAnnouncements(callback: (Response<List<Announcement>, Throwable>)->Unit) {
        thread {
            Thread.sleep(2000) // Nobody trust university system that works too fast
            if (Random.nextBoolean()) {
                uiThread {
                    callback(ErrorResponse(Error("Random error"))) // Nobody trust university system that is fully reliable
                }
            } else {
                val announcements = AnnouncementsList.getAnnouncements(
                    passingStudentsListText = makePassingStudentsListText(),
                    bestStudentsListText = makePassingStudentsListText()
                )
                uiThread {
                    callback(Success(announcements))
                }
            }
        }
    }

    /*
     * Displays a list of students who have got more than 15 points in the semester and a result of
     * at least 50, in alphabetical order (surname then name), in the format: “{name} {surname}, {result}”
     */
    // TODO: Should return passing students list. See PassingStudentsListTest
    fun makePassingStudentsListText(): String = "" // Get students using studentsRepository.getStudents()

    /*
     * Displays the best 10 students so they can get an internship. Comparing students by result
     * (higher is better). To get an internship, students need to have got at least 30 points
     * in the semester and a result of at least 80. The best student gets $5000, the next 3 get
     * $3000 and the next 6 get $1000. Display students in alphabetical order (compare first surname
     * then name) in the format “{name} {surname}, ${internship size}”
     */
    // TODO: Should return students for internship. See BestStudentsListTest
    fun makeBestStudentsList(): String = "" // Get students using studentsRepository.getStudents()
}