package com.workshop.universityannouncementsboard.extra

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.repeat as `Implement it yourself`

class RepeatTest {

    @Test
    fun add3Str() {
        var str = ""
//        repeat(3) { str += "A" }
        assertEquals("AAA", str)
    }

    @Test
    fun add3Int() {
        var i = 0
//        repeat(3) { i++ }
        assertEquals(3, i)
    }

    @Test
    fun add0Int() {
        var i = 0
//        repeat(0) { i++ }
        assertEquals(0, i)
    }
}