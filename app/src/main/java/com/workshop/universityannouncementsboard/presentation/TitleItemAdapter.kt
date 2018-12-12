package com.workshop.universityannouncementsboard.presentation

import com.workshop.universityannouncementsboard.R
import com.workshop.universityannouncementsboard.util.ItemAdapter
import kotlinx.android.synthetic.main.item_title.*

data class TitleItemAdapter(
        private val title: String
) : ItemAdapter(R.layout.item_title) {

    override fun setupView() {
        titleView.text = title
    }
}