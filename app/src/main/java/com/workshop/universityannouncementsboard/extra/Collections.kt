package com.workshop.universityannouncementsboard.extra

// To prevent unintentional stdlib functions usage
import kotlin.collections.Iterable
import kotlin.collections.List
import kotlin.collections.arrayListOf
import kotlin.collections.listOf
import kotlin.collections.filter as stdlibFilter
//import kotlin.collections.flatMap as stdlibFlatMap
import kotlin.collections.map as stdlibMap
import kotlin.collections.onEach as stdlibOnEach

inline fun <T> Iterable<T>.onEach(operation: (T) -> Unit) {
    for (elem in this) {
        operation(elem)
    }
}

inline fun <T, R> Iterable<T>.map(transformation: (T) -> R): List<R> {
    val list = arrayListOf<R>()
    for (elem in this) {
        val element = transformation(elem)
        list.add(element)
    }
    return list
}

inline fun <T, R> Iterable<T>.flatMap(transformation: (T) -> Iterable<R>): List<R> {
    val list = arrayListOf<R>()
    for (elem in this) {
        list.addAll(transformation(elem))
    }
    return list
}

inline fun <T> Iterable<T>.filter(predicate: (T) -> Boolean): List<T> {
    val list = arrayListOf<T>()
    for (elem in this) {
        if (predicate(elem)) {
            list.add(elem)
        }
    }
    return list
}

data class NumberHolder(val number: Int)

fun main(args: Array<String>) {
    val names = listOf("Marcin", "Kuba", "Maja", "Piotr")


    val gropued = names.groupBy { it.first() }
    println(gropued)
    println(gropued['M'])
    println(gropued.flatMap { it.value })

    val associated = names.associateBy { it.first() }
    println(associated)
    println(associated['M'])
}