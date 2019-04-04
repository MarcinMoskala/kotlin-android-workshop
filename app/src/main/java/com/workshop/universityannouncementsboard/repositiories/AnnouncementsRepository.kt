package com.workshop.universityannouncementsboard.repositiories

import com.workshop.universityannouncementsboard.model.*

interface AnnouncementsRepository {

    fun getAnnouncements(callback: (Response<List<Announcement>, Throwable>) -> Unit)
}