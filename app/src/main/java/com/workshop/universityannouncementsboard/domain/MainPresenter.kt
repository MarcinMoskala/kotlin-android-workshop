package com.workshop.universityannouncementsboard.domain

import com.workshop.universityannouncementsboard.model.Failure
import com.workshop.universityannouncementsboard.model.Success
import kotlinx.coroutines.launch

// TODO: Write logic for MainPresenter to pass all tests in MainPresenterTest. Use cases:
// * When onCreate, loads and displays announcements
// * Refresh is displayed during onCreate loading
// * When repository returns error, it is shown on view
// * When different data are served after refresh, new data are displayed
// * During refresh, swipeRefresh is displayed and loading is not

class MainPresenter(
        private val view: MainView,
        private val announcementsRepository: AnnouncementsRepository
) : BasePresenter() {

    fun onCreate(): Unit {
        launch {
            view.loading = true
            refresh()
            view.loading = false
        }
    }

    fun onRefresh() {
        launch {
            view.swipeRefresh = true
            refresh()
            view.swipeRefresh = false
        }
    }

    private suspend fun refresh() {
        when (val resp = announcementsRepository.getAnnouncements()) {
            is Success -> view.showAnnouncements(resp.value)
            is Failure -> view.showError(resp.error)
        }
    }
}
