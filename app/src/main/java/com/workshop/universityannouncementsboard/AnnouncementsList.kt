package com.workshop.universityannouncementsboard

import com.workshop.universityannouncementsboard.model.Announcement

object AnnouncementsList {
     fun getAnnouncements(passingStudentsListText: String, bestStudentsListText: String): List<Announcement> = announcements {
         reminder("If you want to receive internship, you need to provide documents till end of September")
         info {
             title = "Students who are passing:"
             content = passingStudentsListText
         }
         info {
             title = "Internships:"
             content = bestStudentsListText
         }
         reminder("Work hard whole year and prepare to all classes")
         info {
             content = "Checking this app periodically will help you be up to date with your university"
         }
     }
}

fun announcements(init: AnnouncementsBuilder.()->Unit): List<Announcement> {
    return AnnouncementsBuilder().apply(init).list
}

class AnnouncementsBuilder {
    val list = mutableListOf<Announcement>()

    fun reminder(text: String) {
        list += Announcement("Remember!", text)
    }

    fun info(init: InfoBuilder.()->Unit) {
        list += InfoBuilder().apply(init).toAnnouncement()
    }
}

class InfoBuilder {
    var title: String? = null
    var content = ""
    fun toAnnouncement() = Announcement(title, content)
}