package com.workshop.universityannouncementsboard.util

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

open class BaseRecyclerViewAdapter(
        items: List<ItemAdapter>
) : RecyclerView.Adapter<BaseViewHolder>() {

    protected val items = items.toMutableList()

    final override fun getItemCount() = items.size

    final override fun getItemViewType(position: Int) = items[position].layoutId

    final override fun onCreateViewHolder(parent: ViewGroup, layoutId: Int): BaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return BaseViewHolder(view)
    }

    final override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        items[position].bindViewHolder(holder)
    }
}