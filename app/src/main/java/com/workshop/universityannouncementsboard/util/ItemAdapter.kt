package com.workshop.universityannouncementsboard.util

import android.view.*
import androidx.annotation.LayoutRes
import kotlinx.android.extensions.*

abstract class ItemAdapter(@LayoutRes open val layoutId: Int) : LayoutContainer {
    override var containerView: View? = null

    abstract fun setupView()
}