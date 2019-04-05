package com.workshop.universityannouncementsboard.presentation

import android.os.*
import android.support.v7.app.*
import android.support.v7.widget.*
import com.workshop.universityannouncementsboard.*
import com.workshop.universityannouncementsboard.model.*
import com.workshop.universityannouncementsboard.repositiories.*
import com.workshop.universityannouncementsboard.util.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    override var loading: Boolean by bindToVisibility(R.id.progressView)
    override var swipeRefresh: Boolean by bindToSwipeRefresh(R.id.swipeRefreshView)

    private val studentsRepository by lazy { StudentsRepositoryImpl() }
    private val announcementsRepository by lazy { AnnouncementsRepositoryImpl(studentsRepository) }
    private val presenter by lazy { MainPresenter(this, announcementsRepository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onActivityCreated()
    }

    fun onActivityCreated() {
        listView.layoutManager = LinearLayoutManager(this)
        swipeRefreshView.setOnRefreshListener {
            presenter.onRefresh()
        }
        presenter.onCreate()
    }

    override fun showAnnouncements(announcements: List<Announcement>) {
        val titleItem = listOf(TitleItemAdapter("Announcements"))
        val announcementsItems = announcements.map(::AnnouncementItemAdapter)
        listView.adapter = AnnouncementsListAdapter(titleItem + announcementsItems)
    }

    override fun showError(error: Throwable) {
        if (listView.adapter == null) {
            val items = listOf(TitleItemAdapter("Keep refreshing"))
            listView.adapter = AnnouncementsListAdapter(items)
        }
        val message = error.message ?: "Unknown error"
        toast(message)
    }
}