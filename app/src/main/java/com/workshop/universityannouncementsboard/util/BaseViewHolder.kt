package com.workshop.universityannouncementsboard.util

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.extensions.LayoutContainer

class BaseViewHolder(
        override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer