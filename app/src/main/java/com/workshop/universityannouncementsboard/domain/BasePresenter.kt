package com.workshop.universityannouncementsboard.domain

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BasePresenter(
    private val onError: (Throwable) -> Unit = {}
) {
    val scope: CoroutineScope = TODO()

    fun onDestroy() {
    }
}