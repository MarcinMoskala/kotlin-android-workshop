package com.workshop.universityannouncementsboard.presentation

import com.workshop.universityannouncementsboard.R
import com.workshop.universityannouncementsboard.model.Announcement
import com.workshop.universityannouncementsboard.util.ItemAdapter

class AnnouncementItemAdapter(
        private val announcement: Announcement
) : ItemAdapter(R.layout.item_announcement) {

    // TODO: Uncomment this and make it work
    // Hint: Use LayoutContainer from KAE, https://stackoverflow.com/a/45961003
    // override fun BaseViewHolder.onBindViewHolder() {
    //     titleView.text = announcement.title ?: ""
    //     textView.text = announcement.text
    //     if(announcement.title == null) titleView.hide()
    // }
}