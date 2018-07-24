package com.workshop.universityannouncementsboard.util

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

// TODO
open class BaseRecyclerViewAdapter(
        val items: List<ItemAdapter>
) : RecyclerView.Adapter<BaseViewHolder>() {

    final override fun getItemCount() = 0

    final override fun getItemViewType(position: Int) = TODO()

    final override fun onCreateViewHolder(parent: ViewGroup, layoutId: Int): BaseViewHolder {
        TODO()
    }

    final override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        TODO()
    }
}
