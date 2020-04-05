package com.workshop.universityannouncementsboard

import com.workshop.universityannouncementsboard.model.*
import com.workshop.universityannouncementsboard.presentation.*
import com.workshop.universityannouncementsboard.repositiories.*
import io.mockk.*
import org.junit.*
import org.junit.Assert.*

class MainPresenterCallbackTest {

    private val someError = Error("Some error")
    private val someAnnouncements = listOf(Announcement("Some title", "Some text"))

    @Test
    fun `When onCreate, loads and displays announcements`() {
        val view: MainView = mockk(relaxed = true)
        val repo = makeAnnouncementRepositoryReturning(Success(someAnnouncements))
        val presenter = MainPresenter(view, repo)

        // When
        presenter.onCreate()
        repo.invoke()

        // Then
        val capturingSlot = slot<List<Announcement>>()
        verify(ordering = Ordering.ORDERED) {
            view.showAnnouncements(capture(capturingSlot))
        }
        assertEquals(someAnnouncements, capturingSlot.captured)
    }

    @Test
    fun `Refresh is displayed during onCreate loading`() {
        val view: MainView = mockk(relaxed = true)
        val repo = makeAnnouncementRepositoryReturning(Success(listOf()))
        val presenter = MainPresenter(view, repo)

        // When
        presenter.onCreate()
        repo.invoke()

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
        val repo = makeAnnouncementRepositoryReturning(Failure(someError))
        val presenter = MainPresenter(view, repo)

        // When
        presenter.onCreate()
        repo.invoke()

        // Then
        val capturingSlot = slot<Throwable>()
        verify(ordering = Ordering.ORDERED) {
            view.showError(capture(capturingSlot))
        }
        assertEquals(someError, capturingSlot.captured)
    }

    @Test
    fun `When different data are served after refresh, new data are displayed`() {
        val view: MainView = mockk(relaxed = true)
        val repo = makeAnnouncementRepositoryReturning(Success(listOf()), Success(someAnnouncements))
        val presenter = MainPresenter(view, repo)

        // When
        presenter.onCreate()
        repo.invoke()
        presenter.onRefresh()
        repo.invoke()

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
        val repo = makeAnnouncementRepositoryReturning(Success(listOf()))
        val presenter = MainPresenter(view, repo)

        // When
        presenter.onCreate()
        repo.invoke()
        presenter.onRefresh()
        repo.invoke()

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

    private fun makeAnnouncementRepositoryReturning(
        vararg result: Response<List<Announcement>, Throwable>
    ) = object : AnnouncementsRepository {
        var i = 0
        var callback: ((Response<List<Announcement>, Throwable>) -> Unit)? = null

        override fun getAnnouncements(callback: (Response<List<Announcement>, Throwable>) -> Unit) {
            this.callback = callback
        }

        override fun getAnnouncements(): Response<List<Announcement>, Throwable> {
            TODO("Do not use it")
        }

        fun invoke() {
            callback?.invoke(result[i])
            i = (i + 1) % result.size
        }
    }

}