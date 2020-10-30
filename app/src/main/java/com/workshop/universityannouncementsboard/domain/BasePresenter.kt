package com.workshop.universityannouncementsboard.domain

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BasePresenter(
        private val onError: (Throwable) -> Unit = {}
) {

    fun onDestroy() {
    }
}