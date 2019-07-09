package com.workshop.universityannouncementsboard.extra

import org.junit.*
import org.junit.Assert.*

// TODO: Uncomment
//import com.workshop.universityannouncementsboard.extra.flatMap
//import kotlin.collections.flatMap as stdlibFlatMap

class MapTest {

    @Test
    fun mapTests() {
        val numbers = 1..5
        val names = listOf("Mike", "Jane", "Marcin", "John", "James")

        val upper = names.map { it.toUpperCase() }
        val doubled = numbers.map { it * 2 }

        assertEquals(listOf("MIKE", "JANE", "MARCIN", "JOHN", "JAMES"), upper)
        assertEquals(listOf(2, 4, 6, 8, 10), doubled)
    }
}