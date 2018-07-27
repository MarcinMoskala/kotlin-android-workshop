package com.workshop.universityannouncementsboard

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.workshop.universityannouncementsboard.model.Announcement
import com.workshop.universityannouncementsboard.presentation.*
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MainActivityTest {

    @MockK
    lateinit var listViewMock: RecyclerView

    @MockK
    lateinit var swipeRefreshViewMock: SwipeRefreshLayout

    @SpyK
    var activity = MainActivity()

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        every { activity.findViewById<RecyclerView>(R.id.listView) } returns listViewMock
        every { activity.findViewById<SwipeRefreshLayout>(R.id.swipeRefreshView) } returns swipeRefreshViewMock

        mockkConstructor(MainPresenter::class)
        every { anyConstructed<MainPresenter>().onCreate() } just runs
        every { anyConstructed<MainPresenter>().onRefresh() } just runs

        mockkConstructor(LinearLayoutManager::class)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `onCreate sets linear layout manager of listView`() {
        activity.onActivityCreated()
        verify {
            listViewMock.layoutManager = any()
        }
    }

    @Test
    fun `onCreate sets swipe refresh of swipeRefreshView`() {
        activity.onActivityCreated()
        val swipeRefreshListenerSlot = slot<SwipeRefreshLayout.OnRefreshListener>()
        verify {
            swipeRefreshViewMock.setOnRefreshListener(capture(swipeRefreshListenerSlot))
        }

        swipeRefreshListenerSlot.captured.onRefresh()
        verify {
            anyConstructed<MainPresenter>().onRefresh()
        }
    }

    @Test
    fun `onCreate calls presenter onCreate method`() {
        activity.onActivityCreated()
        verify {
            anyConstructed<MainPresenter>().onCreate()
        }
    }

    @Test
    fun `onCreate sets layout manager before it calls presenter onCreate method`() {
        activity.onActivityCreated()
        verify(Ordering.ORDERED) {
            listViewMock.layoutManager = any()
            anyConstructed<MainPresenter>().onCreate()
        }
    }

    @Test
    fun `showAnnouncements sets list adapter with just a title for no announcements`() {
        activity.showAnnouncements(listOf())
        val slot = slot<AnnouncementsListAdapter>()
        verify {
            listViewMock.adapter = capture(slot)
        }
        val adapter = slot.captured
        assertEquals(1, adapter.items.size)
        assertEquals(TitleItemAdapter("Announcements"), adapter.items.single())
    }

    @Test
    fun `showAnnouncements sets list adapter to display title and then all announcements as adapters`() {
        val announcements = listOf(Announcement("AAAA", "CCCC"), Announcement("BBBB", "DDDD"))
        activity.showAnnouncements(announcements)
        val slot = slot<AnnouncementsListAdapter>()
        verify {
            listViewMock.adapter = capture(slot)
        }
        val adapter = slot.captured
        assertEquals(announcements.size + 1, adapter.items.size)
        assertEquals(TitleItemAdapter("Announcements"), adapter.items[0])
        for (i in announcements.indices) {
            assertEquals(AnnouncementItemAdapter(announcements[i]), adapter.items[i + 1])
        }
    }

    @Test
    fun `showError does not set adapter if adapter is set`() {
        every { listViewMock.adapter } returns AnnouncementsListAdapter(listOf())

        activity.showError(Error())
        verify(inverse = true) {
            listViewMock.adapter = any()
        }
    }

    @Test
    fun `showError sets adapter with a title if adapter is null`() {
        every { listViewMock.adapter } returns null

        activity.showError(Error())

        val slot = slot<AnnouncementsListAdapter>()
        verify {
            listViewMock.adapter = capture(slot)
        }
        val adapter = slot.captured
        assertEquals(1, adapter.items.size)
        assertEquals(TitleItemAdapter("Keep refreshing"), adapter.items.single())
    }
}