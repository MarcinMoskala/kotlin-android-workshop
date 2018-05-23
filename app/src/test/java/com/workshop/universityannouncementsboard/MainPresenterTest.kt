package com.workshop.universityannouncementsboard

import com.workshop.universityannouncementsboard.model.Announcement
import com.workshop.universityannouncementsboard.model.ErrorResponse
import com.workshop.universityannouncementsboard.model.Success
import com.workshop.universityannouncementsboard.presentation.MainPresenter
import com.workshop.universityannouncementsboard.presentation.MainView
import com.workshop.universityannouncementsboard.repositiories.AnnouncementsRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verifySequence
import org.junit.Assert.assertEquals
import org.junit.Test

class MainPresenterTest {

    private val someError = Error("Some error")
    private val someAnnouncements = listOf(Announcement("Some title", "Some text"))

    @Test
    fun `When onCreate, loads and displays announcements`() {
        val view: MainView = mockk(relaxed = true)
        val repo: AnnouncementsRepository = mockk(relaxed = true)
        every { repo.getAnnouncements() } returns Success(someAnnouncements)
        val presenter = MainPresenter(view, repo)

        // When
        presenter.onCreate()

        // Then
        val capturingSlot = slot<List<Announcement>>()
        verifySequence {
            view.loading = true
            repo.getAnnouncements()
            view.showAnnouncements(capture(capturingSlot))
            view.loading = false
        }
        assertEquals(someAnnouncements, capturingSlot.captured)
    }

    @Test
    fun `Refresh is displayed during onCreate loading`() {
        val view: MainView = mockk(relaxed = true)
        val repo: AnnouncementsRepository = mockk(relaxed = true)
        every { repo.getAnnouncements() } returns Success(listOf())
        val presenter = MainPresenter(view, repo)

        // When
        presenter.onCreate()

        // Then
        verifySequence {
            view.loading = true
            repo.getAnnouncements()
            view.showAnnouncements(any())
            view.loading = false
        }
    }

    @Test
    fun `When repository returns error, it is shown on view`() {
        val view: MainView = mockk(relaxed = true)
        val repo: AnnouncementsRepository = mockk(relaxed = true)
        every { repo.getAnnouncements() } returns ErrorResponse(someError)
        val presenter = MainPresenter(view, repo)

        // When
        presenter.onCreate()

        // Then
        val capturingSlot = slot<Throwable>()
        verifySequence {
            view.loading = true
            repo.getAnnouncements()
            view.showError(capture(capturingSlot))
            view.loading = false
        }
        assertEquals(someError, capturingSlot.captured)
    }

    @Test
    fun `When different data are served after refresh, new data are displayed`() {
        val view: MainView = mockk(relaxed = true)
        val repo: AnnouncementsRepository = mockk(relaxed = true)
        every { repo.getAnnouncements() } returnsMany listOf(Success(listOf()), Success(someAnnouncements))
        val presenter = MainPresenter(view, repo)

        // When
        presenter.onCreate()
        presenter.onRefresh()

        // Then
        val capturingSlot = slot<List<Announcement>>()
        verifySequence {
            view.loading = true
            repo.getAnnouncements()
            view.showAnnouncements(any())
            view.loading = false

            view.swipeRefresh = true
            repo.getAnnouncements()
            view.showAnnouncements(capture(capturingSlot))
            view.swipeRefresh = false
        }
        assertEquals(someAnnouncements, capturingSlot.captured)
    }

    @Test
    fun `During refresh, swipeRefresh is displayed and loading is not`() {
        val view: MainView = mockk(relaxed = true)
        val repo: AnnouncementsRepository = mockk(relaxed = true)
        every { repo.getAnnouncements() } returns Success(listOf())
        val presenter = MainPresenter(view, repo)

        // When
        presenter.onCreate()
        presenter.onRefresh()

        // Then
        verifySequence {
            view.loading = true
            repo.getAnnouncements()
            view.showAnnouncements(any())
            view.loading = false

            view.swipeRefresh = true
            repo.getAnnouncements()
            view.showAnnouncements(any())
            view.swipeRefresh = false
        }
    }

}