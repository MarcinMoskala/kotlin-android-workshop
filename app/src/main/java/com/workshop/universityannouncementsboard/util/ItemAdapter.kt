package com.workshop.universityannouncementsboard.util

import android.support.annotation.LayoutRes
import android.view.View
import kotlinx.android.extensions.LayoutContainer

abstract class ItemAdapter(@LayoutRes open val layoutId: Int): LayoutContainer {
    override var containerView: View? = null

    abstract fun setupView()
}