package com.workshop.universityannouncementsboard

import com.workshop.universityannouncementsboard.model.Announcement

object AnnouncementsList {
    fun getAnnouncements(passingStudentsListText: String, bestStudentsListText: String) = listOf(
            Announcement(
                    "Remember!",
                    "If you want to receive internship, you need to provide documents till end of September"
            ),
            Announcement(
                    "Students who are passing:",
                    passingStudentsListText
            ),
            Announcement(
                    "Internships:",
                    bestStudentsListText
            ),
            Announcement(
                    "Remember!",
                    "Work hard whole year and prepare to all classes"
            )
    )
}