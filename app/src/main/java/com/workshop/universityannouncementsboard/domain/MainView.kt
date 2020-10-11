package com.workshop.universityannouncementsboard.domain

import com.workshop.universityannouncementsboard.model.Announcement

interface MainView {
    var loading: Boolean
    var swipeRefresh: Boolean
    fun showAnnouncements(announcements: List<Announcement>)
    fun showError(error: Throwable)
}