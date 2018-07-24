package com.workshop.universityannouncementsboard.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.workshop.universityannouncementsboard.R
import com.workshop.universityannouncementsboard.model.Announcement
import com.workshop.universityannouncementsboard.repositiories.AnnouncementsRepositoryImpl
import com.workshop.universityannouncementsboard.repositiories.StudentsRepositoryImpl

class MainActivity : AppCompatActivity(), MainView {

    // TODO: Write property delegate that will bind it to visibility of R.id.progressView
    // override var loading: Boolean by bindToVisibility(R.id.progressView)
    override var loading: Boolean = false
    // TODO: Write property delegate that will bind it to swipe refresh on R.id.swipeRefreshView
    // override var swipeRefresh: Boolean by bindToSwipeRefresh(R.id.swipeRefreshView)
    override var swipeRefresh: Boolean = false

    private val studentsRepository by lazy { StudentsRepositoryImpl() }
    private val announcementsRepository by lazy { AnnouncementsRepositoryImpl(studentsRepository) }
    private val presenter by lazy { MainPresenter(this, announcementsRepository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onActivityCreated()
    }

    fun onActivityCreated() {
        // TODO
    }

    override fun showAnnouncements(announcements: List<Announcement>) {
        // TODO
    }

    override fun showError(error: Throwable) {
        // TODO
    }
}