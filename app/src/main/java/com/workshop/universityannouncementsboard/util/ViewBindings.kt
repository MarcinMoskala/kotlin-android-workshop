package com.workshop.universityannouncementsboard.util

import android.app.*
import android.support.annotation.*
import android.support.v4.widget.*
import android.view.*
import kotlin.properties.*
import kotlin.reflect.*

// TODO Implement bindToVisibility and bindToSwipeRefresh here
fun Activity.bindToVisibility(@IdRes id: Int): ReadWriteProperty<Any?, Boolean> =
    VisibilityBinding(lazy { findViewById<View>(id) })

fun Activity.bindToSwipeRefresh(@IdRes id: Int): ReadWriteProperty<Any?, Boolean> =
    SwipeRefreshBinding(lazy { findViewById<SwipeRefreshLayout>(id) })

private class VisibilityBinding(lazyView: Lazy<View>) : ReadWriteProperty<Any?, Boolean> {
    val view: View by lazyView

    override fun getValue(thisRef: Any?, property: KProperty<*>): Boolean =
        view.visibility == View.VISIBLE

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Boolean) {
        view.visibility = if(value) View.VISIBLE else View.GONE
    }
}

private class SwipeRefreshBinding(lazyView: Lazy<SwipeRefreshLayout>) : ReadWriteProperty<Any?, Boolean> {
    val swipeRefresh: SwipeRefreshLayout by lazyView

    override fun getValue(thisRef: Any?, property: KProperty<*>): Boolean =
        swipeRefresh.isRefreshing

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Boolean) {
        swipeRefresh.isRefreshing = value
    }
}

