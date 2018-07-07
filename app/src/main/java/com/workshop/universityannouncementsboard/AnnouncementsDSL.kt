package com.workshop.universityannouncementsboard

import com.workshop.universityannouncementsboard.model.Announcement

fun announcements(init: AnnouncementsConfig.() -> Unit): List<Announcement> {
    val config = AnnouncementsConfig().apply(init)
    return config.list
}

class AnnouncementsConfig {
    val list = mutableListOf<Announcement>()

    fun reminder(text: String) {
        list.add(Announcement(
                title = "Remember!",
                text = text
        ))
    }

    fun info(init: InfoConfig.() -> Unit) {
        val announcementConfig = InfoConfig().apply(init)
        val announcement = announcementConfig.toAnnouncement()
        list.add(announcement)
    }
}

class InfoConfig {
    var content: String? = null
    var title: String? = null

    fun toAnnouncement(): Announcement = Announcement(
            title = title,
            text = content ?: ""
    )
}