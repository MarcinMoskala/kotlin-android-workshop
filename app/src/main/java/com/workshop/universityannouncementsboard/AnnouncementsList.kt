package com.workshop.universityannouncementsboard

import com.workshop.universityannouncementsboard.model.Announcement

object AnnouncementsList {
    fun getAnnouncements(passingStudentsListText: String, bestStudentsListText: String): List<Announcement> = listOf(
            Announcement("Students who are passing:", passingStudentsListText),
            Announcement("Internships:", bestStudentsListText),
    )
}