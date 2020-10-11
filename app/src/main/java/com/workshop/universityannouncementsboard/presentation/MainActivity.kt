package com.workshop.universityannouncementsboard.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.workshop.universityannouncementsboard.R
import com.workshop.universityannouncementsboard.model.Announcement
import com.workshop.universityannouncementsboard.repositiories.AnnouncementsRepositoryImpl
import com.workshop.universityannouncementsboard.repositiories.StudentsRepositoryImpl
import com.workshop.universityannouncementsboard.util.toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(), MainView {

    // TODO: Write property delegate that will bind it to visibility of R.id.progressView. Usage should look this way
    // override var loading: Boolean by bindToVisibility(R.id.progressView)
    override var loading: Boolean by Delegates.observable(false) { _, _, newValue ->
        progressView.visibility = if (newValue) View.VISIBLE else View.GONE
    }

    // TODO: Write property delegate that will bind it to swipe refresh on R.id.swipeRefreshView. Usage should look this way
    // override var swipeRefresh: Boolean by bindToSwipeRefresh(R.id.swipeRefreshView)
    override var swipeRefresh: Boolean by Delegates.observable(false) { _, _, newValue ->
        swipeRefreshView.isRefreshing = newValue
    }

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
        val titleItem = TitleItemAdapter("Announcements")
        val announcementsItems = announcements.map(::AnnouncementItemAdapter)
        listView.adapter = AnnouncementsListAdapter(listOf(titleItem) + announcementsItems)
    }

    override fun showError(error: Throwable) {
        if (listView.adapter == null) {
            listView.adapter = AnnouncementsListAdapter(listOf(TitleItemAdapter("Keep refreshing")))
        }
        toast(error.message ?: "Unknown error")
    }
}