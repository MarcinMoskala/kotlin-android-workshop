package com.workshop.universityannouncementsboard.presentation

import com.workshop.universityannouncementsboard.repositiories.AnnouncementsRepository

// TODO: Write logic for this presenter
class MainPresenter(
        private val view: MainView,
        private val announcementsRepository: AnnouncementsRepository
) {

    fun onCreate() {
    }

    fun onRefresh() {
    }
}