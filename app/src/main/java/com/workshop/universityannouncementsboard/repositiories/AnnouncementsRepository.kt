package com.workshop.universityannouncementsboard.repositiories

import com.workshop.universityannouncementsboard.model.Announcement
import com.workshop.universityannouncementsboard.model.Response

interface AnnouncementsRepository {

    fun getAnnouncements(callback: (Response<List<Announcement>, Throwable>)->Unit)
}