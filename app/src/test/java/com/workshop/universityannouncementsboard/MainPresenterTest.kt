package com.workshop.universityannouncementsboard

import com.workshop.universityannouncementsboard.model.Announcement
import com.workshop.universityannouncementsboard.model.Failure
import com.workshop.universityannouncementsboard.model.Success
import com.workshop.universityannouncementsboard.domain.MainPresenter
import com.workshop.universityannouncementsboard.domain.MainView
import com.workshop.universityannouncementsboard.domain.AnnouncementsRepository
import io.mockk.*
import org.junit.Assert.assertEquals
import org.junit.Test

class MainPresenterTest {

    private val someError = Error("Some error")
    private val someAnnouncements = listOf(Announcement("Some title", "Some text"))

    @Test
    fun `When onCreate, loads and displays announcements`() {
        val view: MainView = mockk(relaxed = true)
        val repo: AnnouncementsRepository = mockk(relaxed = true)
        coEvery { repo.getAnnouncements() } returns Success(someAnnouncements)
        val presenter = MainPresenter(view, repo)

        // When
        presenter.onCreate()

        // Then
        val capturingSlot = slot<List<Announcement>>()
        coVerify(ordering = Ordering.ORDERED) {
            repo.getAnnouncements()
            view.showAnnouncements(capture(capturingSlot))
        }
        assertEquals(someAnnouncements, capturingSlot.captured)
    }

    @Test
    fun `Refresh is displayed during onCreate loading`() {
        val view: MainView = mockk(relaxed = true)
        val repo: AnnouncementsRepository = mockk(relaxed = true)
        coEvery { repo.getAnnouncements() } returns Success(listOf())
        val presenter = MainPresenter(view, repo)

        // When
        presenter.onCreate()

        // Then
        coVerifySequence {
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
        coEvery { repo.getAnnouncements() } returns Failure(someError)
        val presenter = MainPresenter(view, repo)

        // When
        presenter.onCreate()

        // Then
        val capturingSlot = slot<Throwable>()
        coVerify(ordering = Ordering.ORDERED) {
            repo.getAnnouncements()
            view.showError(capture(capturingSlot))
        }
        assertEquals(someError, capturingSlot.captured)
    }

    @Test
    fun `When different data are served after refresh, new data are displayed`() {
        val view: MainView = mockk(relaxed = true)
        val repo: AnnouncementsRepository = mockk(relaxed = true)
        coEvery { repo.getAnnouncements() } returnsMany listOf(Success(listOf()), Success(someAnnouncements))
        val presenter = MainPresenter(view, repo)

        // When
        presenter.onCreate()
        presenter.onRefresh()

        // Then
        val capturingSlot = slot<List<Announcement>>()
        coVerifySequence {
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
        coEvery { repo.getAnnouncements() } returns Success(listOf())
        val presenter = MainPresenter(view, repo)

        // When
        presenter.onCreate()
        presenter.onRefresh()

        // Then
        coVerifySequence {
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