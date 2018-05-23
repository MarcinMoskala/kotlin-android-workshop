package com.workshop.universityannouncementsboard.presentation

import com.workshop.universityannouncementsboard.R
import com.workshop.universityannouncementsboard.model.Announcement
import com.workshop.universityannouncementsboard.util.ItemAdapter

class AnnouncementItemAdapter(
        private val announcement: Announcement
) : ItemAdapter(R.layout.item_announcement) {

// TODO: I make it possible to set it this way (use experimental KAE feature)
//    override fun BaseViewHolder.onBindViewHolder() {
//        titleView.text = announcement.title
//        textView.text = announcement.text
//    }
}