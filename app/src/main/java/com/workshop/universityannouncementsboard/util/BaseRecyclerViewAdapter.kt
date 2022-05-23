package com.workshop.universityannouncementsboard.util

import android.view.*
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter(
    val items: List<ItemAdapter>
) : RecyclerView.Adapter<BaseViewHolder>() {

    final override fun getItemCount() = items.size

    final override fun getItemViewType(position: Int) = items[position].layoutId

    final override fun onCreateViewHolder(parent: ViewGroup, layoutId: Int): BaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return BaseViewHolder(view)
    }

    final override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        items[position].apply {
            containerView = holder.view
            setupView()
        }
    }
}