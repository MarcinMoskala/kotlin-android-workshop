package com.workshop.universityannouncementsboard.extra

import org.junit.*
import org.junit.Assert.*

class RationalTests {

    @Test
    fun testIntExtension() {
        assertEquals(RationalNumber(4, 1), 4.r())
    }

    @Test
    fun testPairExtension() {
        assertEquals(RationalNumber(2, 3), Pair(2, 3).r())
    }
}