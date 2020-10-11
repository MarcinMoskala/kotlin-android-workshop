package com.workshop.universityannouncementsboard.extra

import kotlin.properties.ReadWriteProperty
import kotlin.system.measureTimeMillis

fun <T> mutableLazy(initializer: () -> T): ReadWriteProperty<Any?, T> = TODO()