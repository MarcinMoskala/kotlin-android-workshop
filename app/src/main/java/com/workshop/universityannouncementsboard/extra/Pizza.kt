package com.workshop.universityannouncementsboard.extra

data class Pizza(
    val name: String,
    val cheese: Int = 0,
    val pineapple: Int = 0,
    val ham: Int = 0
) {
    object Factory {
        fun hawaiian() = Pizza("Hawaiian", cheese = 1, pineapple = 1, ham = 1)
    }

    companion object {
        fun hawaiian() = Pizza("Hawaiian", cheese = 1, pineapple = 1, ham = 1)
    }
}

object PizzaFactory {
    fun hawaiian() = Pizza("Hawaiian", cheese = 1, pineapple = 1, ham = 1)
}

fun main() {
    val hawaiian = Pizza.hawaiian()
    print(hawaiian) // Pizza(name=Hawaiian, cheese=1, pineapple=1, ham=1)

    val hawaiian2 = PizzaFactory.hawaiian()
    print(hawaiian2) // Pizza(name=Hawaiian, cheese=1, pineapple=1, ham=1)

    val hawaiian3 = Pizza.Factory.hawaiian()
    print(hawaiian3) // Pizza(name=Hawaiian, cheese=1, pineapple=1, ham=1)
}