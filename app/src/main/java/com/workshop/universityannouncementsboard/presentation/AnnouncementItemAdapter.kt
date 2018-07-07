package com.workshop.universityannouncementsboard.presentation

import com.workshop.universityannouncementsboard.R
import com.workshop.universityannouncementsboard.model.Announcement
import com.workshop.universityannouncementsboard.util.BaseViewHolder
import com.workshop.universityannouncementsboard.util.ItemAdapter
import com.workshop.universityannouncementsboard.util.hide
import kotlinx.android.synthetic.main.item_announcement.*

class AnnouncementItemAdapter(
        private val announcement: Announcement
) : ItemAdapter(R.layout.item_announcement) {

    override fun BaseViewHolder.onBindViewHolder() {
        titleView.text = announcement.title ?: ""
        textView.text = announcement.text
        if (announcement.title == null) titleView.hide()
    }
}