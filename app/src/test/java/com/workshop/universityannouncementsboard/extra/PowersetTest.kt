package com.workshop.universityannouncementsboard.extra

import org.junit.Assert.*
import org.junit.Test

class PowersetTest {

    @Test
    fun `Powerset of empty list is only empty list`() {
        val emptyList = setOf<Int>()
        assertEquals(setOf(emptyList), emptyList.powerset())
    }

    @Test
    fun `Powerset of list with single element is empty list and single element`() {
        assertEquals(setOf(setOf(1), setOf()), setOf(1).powerset())
    }

    @Test
    fun `Powerset simple example test`() {
        val set = setOf(
                setOf(1, 2, 3),
                setOf(1, 2),
                setOf(1, 3),
                setOf(2, 3),
                setOf(1),
                setOf(2),
                setOf(3),
                setOf())
        assertEquals(set, setOf(1, 2, 3).powerset())
    }

    @Test
    fun `Size of n element set powerset is 2^n`() {
        for(n in 1..6) {
            val set = (1..n).toSet()
            val size = 2.pow(n)
            assertEquals(size, set.powerset().size)
        }
    }

    private fun Int.pow(power: Int): Int = Math.pow(this.toDouble(), power.toDouble()).toInt()
}