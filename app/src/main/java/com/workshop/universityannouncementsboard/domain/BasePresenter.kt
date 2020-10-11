package com.workshop.universityannouncementsboard.domain

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BasePresenter(
        private val onError: (Throwable) -> Unit = {}
): CoroutineScope {
    private val handler = CoroutineExceptionHandler { _, throwable -> onError(throwable) }
    override val coroutineContext: CoroutineContext = Dispatchers.Main + SupervisorJob() + handler

    fun onDestroy() {
        coroutineContext.cancelChildren()
    }
}