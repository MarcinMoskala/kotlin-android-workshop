package com.workshop.universityannouncementsboard.extra

import org.junit.*
import org.junit.Assert.*

class TreeToStringTest {

    @Test
    fun `If we have a leaf, it's value is displayed`() {
        assertEquals("A", Leaf("A").toString())
        assertEquals("BBB", Leaf("BBB").toString())
    }

    @Test
    fun `If we have a node, it's displayed as '(left, right)'`() {
        assertEquals("(A, B)", Node(Leaf("A"), Leaf("B")).toString())
        assertEquals("(B, C)", Node(Leaf("B"), Leaf("C")).toString())
    }

    @Test
    fun `Nested nodes are displayed same as nodes but node is used instead of element'(left, right)'`() {
        assertEquals("((B, C), D)", Node(Node(Leaf("B"), Leaf("C")), Leaf("D")).toString())
        assertEquals("(((B, C), C), (B, C))", Node(Node(Node(Leaf("B"), Leaf("C")), Leaf("C")), Node(Leaf("B"), Leaf("C"))).toString())
    }
}