package com.workshop.universityannouncementsboard.domain

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class BasePresenter(
    private val onError: (Throwable) -> Unit = {}
) {
    val scope: CoroutineScope = CoroutineScope(EmptyCoroutineContext)

    fun onDestroy() {
    }
}