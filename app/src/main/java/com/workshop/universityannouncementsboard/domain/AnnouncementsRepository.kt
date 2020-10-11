package com.workshop.universityannouncementsboard.domain

import com.workshop.universityannouncementsboard.model.Announcement
import com.workshop.universityannouncementsboard.model.Response

interface AnnouncementsRepository {

    suspend fun getAnnouncements(): Response<List<Announcement>, Throwable>
}