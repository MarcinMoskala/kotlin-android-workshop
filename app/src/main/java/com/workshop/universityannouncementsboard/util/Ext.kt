package com.workshop.universityannouncementsboard.util

import android.content.Context
import android.view.View
import android.widget.Toast

fun Context.toast(message: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, message, duration).show()
}

fun View.hide() {
    this.visibility = View.GONE
}