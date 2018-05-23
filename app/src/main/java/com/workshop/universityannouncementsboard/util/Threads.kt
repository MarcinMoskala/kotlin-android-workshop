package com.workshop.universityannouncementsboard.util

import android.os.Handler
import android.os.Looper

inline fun runOnUiThread(crossinline f: () -> Unit) {
    Handler(Looper.getMainLooper()).post { f() }
}