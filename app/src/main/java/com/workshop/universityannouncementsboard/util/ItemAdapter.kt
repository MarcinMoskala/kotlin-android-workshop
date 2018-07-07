package com.workshop.universityannouncementsboard.util

import android.support.annotation.LayoutRes

abstract class ItemAdapter(@LayoutRes open val layoutId: Int) {

    fun bindViewHolder(holder: BaseViewHolder) {
        holder.onBindViewHolder()
    }

    abstract fun BaseViewHolder.onBindViewHolder()
}