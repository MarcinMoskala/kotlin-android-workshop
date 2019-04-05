package com.workshop.universityannouncementsboard

import com.workshop.universityannouncementsboard.model.*
import com.workshop.universityannouncementsboard.presentation.*
import com.workshop.universityannouncementsboard.repositiories.*
import io.mockk.*
import org.junit.*
import org.junit.Assert.*

class MainPresenterTest {

    private val someError = Error("Some error")
    private val someAnnouncements = listOf(Announcement("Some title", "Some text"))

    @Test
    fun `When onCreate, loads and displays announcements`() {
        val view: MainView = mockk(relaxed = true)
        val repo: AnnouncementsRepository = object : AnnouncementsRepository {
            override fun getAnnouncements(callback: (Response<List<Announcement>, Throwable>) -> Unit) {
                callback(Success(someAnnouncements))
            }
        }
        val presenter = MainPresenter(view, repo)

        // When
        presenter.onCreate()

        // Then
        val capturingSlot = slot<List<Announcement>>()
        verify(Ordering.SEQUENCE) {
            view.loading = true
            view.showAnnouncements(capture(capturingSlot))
            view.loading = false
        }
        assertEquals(someAnnouncements, capturingSlot.captured)
    }

    @Test
    fun `Refresh is displayed during onCreate loading`() {
        val view: MainView = mockk(relaxed = true)
        val repo: AnnouncementsRepository = object : AnnouncementsRepository {
            override fun getAnnouncements(callback: (Response<List<Announcement>, Throwable>) -> Unit) {
                callback(Success(listOf()))
            }
        }
        val presenter = MainPresenter(view, repo)

        // When
        presenter.onCreate()

        // Then
        verifySequence {
            view.loading = true
            view.showAnnouncements(any())
            view.loading = false
        }
    }

    @Test
    fun `When repository returns error, it is shown on view`() {
        val view: MainView = mockk(relaxed = true)
        val repo: AnnouncementsRepository = object : AnnouncementsRepository {
            override fun getAnnouncements(callback: (Response<List<Announcement>, Throwable>) -> Unit) {
                callback(ErrorResponse(someError))
            }
        }
        val presenter = MainPresenter(view, repo)

        // When
        presenter.onCreate()

        // Then
        val capturingSlot = slot<Throwable>()
        verifySequence {
            view.loading = true
            view.showError(capture(capturingSlot))
            view.loading = false
        }
        assertEquals(someError, capturingSlot.captured)
    }

    @Test
    fun `When different data are served after refresh, new data are displayed`() {
        val view: MainView = mockk(relaxed = true)
        val repo: AnnouncementsRepository = object : AnnouncementsRepository {
            var first = true
            override fun getAnnouncements(callback: (Response<List<Announcement>, Throwable>) -> Unit) {
                if(first) {
                    first = false
                    callback(Success(listOf()))
                } else {
                    callback(Success(someAnnouncements))
                }
            }
        }
        val presenter = MainPresenter(view, repo)

        // When
        presenter.onCreate()
        presenter.onRefresh()

        // Then
        val capturingSlot = slot<List<Announcement>>()
        verifySequence {
            view.loading = true
            view.showAnnouncements(any())
            view.loading = false

            view.swipeRefresh = true
            view.showAnnouncements(capture(capturingSlot))
            view.swipeRefresh = false
        }
        assertEquals(someAnnouncements, capturingSlot.captured)
    }

    @Test
    fun `During refresh, swipeRefresh is displayed and loading is not`() {
        val view: MainView = mockk(relaxed = true)
        val repo: AnnouncementsRepository = object : AnnouncementsRepository {
            override fun getAnnouncements(callback: (Response<List<Announcement>, Throwable>) -> Unit) {
                callback(Success(listOf()))
            }
        }
        val presenter = MainPresenter(view, repo)

        // When
        presenter.onCreate()
        presenter.onRefresh()

        // Then
        verifySequence {
            view.loading = true
            view.showAnnouncements(any())
            view.loading = false

            view.swipeRefresh = true
            view.showAnnouncements(any())
            view.swipeRefresh = false
        }
    }

}