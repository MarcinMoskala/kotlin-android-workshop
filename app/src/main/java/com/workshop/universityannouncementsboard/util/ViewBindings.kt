package com.workshop.universityannouncementsboard.util

import android.app.Activity
import android.support.annotation.IdRes
import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun Activity.bindToVisibility(@IdRes viewId: Int): ReadWriteProperty<Any?, Boolean> = ViewVisibilityBinding { findViewById(viewId) }

private class ViewVisibilityBinding(viewProvider: () -> View) : ReadWriteProperty<Any?, Boolean> {

    val view by lazy(viewProvider)

    override fun getValue(thisRef: Any?, property: KProperty<*>): Boolean {
        return view.visibility == View.VISIBLE
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Boolean) {
        uiThread {
            view.visibility = if (value) View.VISIBLE else View.GONE
        }
    }
}

fun Activity.bindToSwipeRefresh(@IdRes viewId: Int): ReadWriteProperty<Any?, Boolean> = SwipeRefreshBinding { findViewById(viewId) }

private class SwipeRefreshBinding(viewProvider: () -> SwipeRefreshLayout) : ReadWriteProperty<Any?, Boolean> {

    val view by lazy(viewProvider)

    override fun getValue(thisRef: Any?, property: KProperty<*>): Boolean {
        return view.isRefreshing
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Boolean) {
        uiThread {
            view.isRefreshing = value
        }
    }
}
