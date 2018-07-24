package com.workshop.universityannouncementsboard.presentation

import com.workshop.universityannouncementsboard.R
import com.workshop.universityannouncementsboard.util.ItemAdapter

data class TitleItemAdapter(
        private val title: String
) : ItemAdapter(R.layout.item_title) {

    // TODO: Uncomment this and make it work
    // Hint: Use LayoutContainer from KAE, https://stackoverflow.com/a/45961003
    // override fun BaseViewHolder.onBindViewHolder() {
    //     titleView.text = title
    // }
}