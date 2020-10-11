package com.workshop.universityannouncementsboard.presentation

import com.workshop.universityannouncementsboard.model.*
import com.workshop.universityannouncementsboard.model.ErrorResponse
import com.workshop.universityannouncementsboard.model.Success
import com.workshop.universityannouncementsboard.repositiories.AnnouncementsRepository
import java.lang.Error

// TODO: Write logic for MainPresenter to pass all tests in MainPresenterTest. Use cases:
// * When onCreate, loads and displays announcements
// * Refresh is displayed during onCreate loading
// * When repository returns error, it is shown on view
// * When different data are served after refresh, new data are displayed
// * During refresh, swipeRefresh is displayed and loading is not

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
