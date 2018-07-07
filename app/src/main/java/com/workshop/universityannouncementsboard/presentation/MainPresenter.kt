package com.workshop.universityannouncementsboard.presentation

import com.workshop.universityannouncementsboard.model.ErrorResponse
import com.workshop.universityannouncementsboard.model.Success
import com.workshop.universityannouncementsboard.repositiories.AnnouncementsRepository

class MainPresenter(
        private val view: MainView,
        private val announcementsRepository: AnnouncementsRepository
) {

    fun onCreate() {
        view.loading = true
        refresh()
        view.loading = false
    }

    fun onRefresh() {
        view.swipeRefresh = true
        refresh()
        view.swipeRefresh = false
    }

    private fun refresh() {
        val resp = announcementsRepository.getAnnouncements()
        when (resp) {
            is Success -> view.showAnnouncements(resp.value)
            is ErrorResponse -> view.showError(resp.error)
        }
    }
}
