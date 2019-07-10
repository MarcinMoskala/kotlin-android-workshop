package com.workshop.universityannouncementsboard.extra.javatask

class KotlinPerson(
    var name: String = "",
    var age: Int = -1
) {
    val isMature: Boolean
        get() = age > 18
}