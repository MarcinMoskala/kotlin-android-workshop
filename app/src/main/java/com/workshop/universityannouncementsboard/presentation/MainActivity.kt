package com.workshop.universityannouncementsboard.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.workshop.universityannouncementsboard.R
import com.workshop.universityannouncementsboard.model.Announcement
import com.workshop.universityannouncementsboard.repositiories.AnnouncementsRepositoryImpl
import com.workshop.universityannouncementsboard.repositiories.StudentsRepositoryImpl
import com.workshop.universityannouncementsboard.util.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity(), MainView {

    override var loading: Boolean by bindToVisibility(R.id.progressView)
    override var swipeRefresh: Boolean by bindToSwipeRefresh(R.id.swipeRefreshView)

    private val studentsRepository by lazy { StudentsRepositoryImpl() }
    private val announcementsRepository by lazy { AnnouncementsRepositoryImpl(studentsRepository) }
    private val presenter by lazy { MainPresenter(this, announcementsRepository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView.layoutManager = LinearLayoutManager(this)
        swipeRefreshView.setOnRefreshListener {
            thread {
                presenter.onRefresh()
            }
        }
        thread {
            presenter.onCreate()
        }
    }

    override fun showAnnouncements(announcements: List<Announcement>) {
        uiThread {
            val titleItem = TitleItemAdapter("Announcements")
            val announcementsItems = announcements.map(::AnnouncementItemAdapter)
            listView.adapter = AnnouncementsListAdapter(listOf(titleItem) + announcementsItems)
        }
    }

    override fun showError(error: Throwable) {
        uiThread {
            if (listView.adapter == null) {
                listView.adapter = AnnouncementsListAdapter(listOf(TitleItemAdapter("Keep refreshing")))
            }
            toast(error.message ?: "Unknown error")
        }
    }
}