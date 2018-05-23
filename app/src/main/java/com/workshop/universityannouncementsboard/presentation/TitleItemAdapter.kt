package com.workshop.universityannouncementsboard.presentation

import com.workshop.universityannouncementsboard.R
import com.workshop.universityannouncementsboard.util.ItemAdapter

class TitleItemAdapter(
        private val title: String
) : ItemAdapter(R.layout.item_title) {

    // TODO: I make it possible to set it this way (use experimental KAE feature)
//    override fun BaseViewHolder.onBindViewHolder() {
//        titleView.text = title
//    }
}