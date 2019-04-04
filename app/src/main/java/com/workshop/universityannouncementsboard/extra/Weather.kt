package com.workshop.universityannouncementsboard.extra

import android.graphics.*

class Weather {

    fun updateWeather(degrees: Int) {
        val (description, color) = when {
            degrees < 5 -> Pair("cold", Color.BLUE)
            degrees < 23 -> "mild" to Color.YELLOW
            else -> "hot" to Color.RED
        }
        // ...
    }
}