package com.workshop.universityannouncementsboard.extra

import org.junit.Assert.*
import org.junit.*

// TODO: Uncomment
import com.workshop.universityannouncementsboard.extra.flatMap
import kotlin.collections.flatMap as stdlibFlatMap

class FlatMapTest {

    @Test
    fun `When empty collections are produced, flatMap returns an empty collection as well`() {
        assertEquals(emptyList<Int>(), (1..100).flatMap { emptyList<Int>() })
        assertEquals(emptyList<String>(), (1..100).flatMap { emptyList<String>() })
    }

    @Test
    fun `When a collection with a single element produced, flatMap returns a list with this elements`() {
        val list = List(1000) { it }
        assertEquals(list, list.flatMap { listOf(it) })
        assertEquals(list, list.flatMap { setOf(it) })
    }

    @Test
    fun `When a few elements are produced, they are accumulated`() {
        val elems = 1..5
        assertEquals(listOf(1, 101, 2, 102, 3, 103, 4, 104, 5, 105), elems.flatMap { listOf(it, it + 100) })
    }
}