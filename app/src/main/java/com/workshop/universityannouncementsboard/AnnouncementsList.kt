package com.workshop.universityannouncementsboard

import com.workshop.universityannouncementsboard.model.Announcement

object AnnouncementsList {
    fun getAnnouncements(passingStudentsListText: String, bestStudentsListText: String) = listOf(
            Announcement(
                    "Students who are passing:",
                    passingStudentsListText
            ),
            Announcement(
                    "Internships:",
                    bestStudentsListText
            )
    )

    // TODO: This programming magic is too hard for university employees. They want to express announcements in following way:
    // fun getAnnouncements(passingStudentsListText: String, bestStudentsListText: String) = announcements {
    //     reminder("If you want to receive internship, you need to provide documents till end of September")
    //     info {
    //         title = "Students who are passing:"
    //         content = passingStudentsListText
    //     }
    //     info {
    //         title = "Internships:"
    //         content = bestStudentsListText
    //     }
    //     reminder("Work hard whole year and prepare to all classes")
    //     info {
    //         content = "Checking this app periodically will help you be up to date with your university"
    //     }
    // }
}