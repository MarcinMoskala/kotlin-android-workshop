package com.workshop.universityannouncementsboard.extra

import io.mockk.*
import org.junit.Assert.*
import org.junit.Test

// TODO: Uncomment it
//import kotlin.let as `Implement it yourself`
//import kotlin.apply as `Implement it yourself 2`

class ScopeFunctionsTest {

    @Test
    fun letSafeCallTest() {
        var called = false
        val isNull: String? = null
        val isStr: String? = "AAA"

        isNull?.let {
            called = true
        }
        assertFalse(called)

        isStr?.let {
            called = true
        }
        assertTrue(called)
    }

    @Test
    fun letProcessingTest() {
        data class A(val value: String)
        data class Decorator(val a: A)

        val d = A("A").let(::Decorator)
        assertEquals(Decorator(A("A")), d)
    }

    @Test
    fun applyInitTest() {
        data class Dialog(val name: String) {
            var okButtonLabel = ""
            var cancelButtonLabel = ""

            var setup = false
                private set

            fun setup() {
                setup = true
            }
        }

        val d = Dialog("Name").apply {
            okButtonLabel = "OK"
            cancelButtonLabel = "Cancel"
            setup()
        }
        assertEquals("Name", d.name)
        assertEquals("OK", d.okButtonLabel)
        assertEquals("Cancel", d.cancelButtonLabel)
        assertTrue(d.setup)
    }


}