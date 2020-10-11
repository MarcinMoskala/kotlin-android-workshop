package com.workshop.universityannouncementsboard.extra

import io.mockk.*
import org.junit.Assert.assertEquals
import org.junit.Test

class BasicsTest {

    @Test
    fun `FizzBuzz starts from 1 and 2`() {
        val text = fuzzBuzzPrinted()
        assertEquals("1", text[0])
        assertEquals("2", text[1])
    }

    @Test
    fun `Third FizzBuzz element is Fizz`() {
        assertEquals("Fizz", fuzzBuzzPrinted()[2])
    }

    @Test
    fun `Fifth FizzBuzz element is Buzz`() {
        val text = fuzzBuzzPrinted()
        assertEquals("Buzz", text[4])
    }

    @Test
    fun `Fifteenth FizzBuzz element is FizzBuzz`() {
        val text = fuzzBuzzPrinted()
        assertEquals("FizzBuzz", text[14])
    }

    @Test
    fun fizzBuzzTest() {
        val text = fuzzBuzzPrinted()
        val solution = "1\n2\nFizz\n4\nBuzz\nFizz\n7\n8\nFizz\nBuzz\n11\nFizz\n13\n14\nFizzBuzz\n16\n17\nFizz\n19\nBuzz\nFizz\n22\n23\nFizz\nBuzz\n26\nFizz\n28\n29\nFizzBuzz\n31\n32\nFizz\n34\nBuzz\nFizz\n37\n38\nFizz\nBuzz\n41\nFizz\n43\n44\nFizzBuzz\n46\n47\nFizz\n49\nBuzz\nFizz\n52\n53\nFizz\nBuzz\n56\nFizz\n58\n59\nFizzBuzz\n61\n62\nFizz\n64\nBuzz\nFizz\n67\n68\nFizz\nBuzz\n71\nFizz\n73\n74\nFizzBuzz\n76\n77\nFizz\n79\nBuzz\nFizz\n82\n83\nFizz\nBuzz\n86\nFizz\n88\n89\nFizzBuzz\n91\n92\nFizz\n94\nBuzz\nFizz\n97\n98\nFizz\nBuzz"
        assertEquals(solution, text.map { it.trim() }.joinToString(separator = "\n"))
    }

    @Test
    fun fibTests() {
        assertEquals(1, fib(0))
        assertEquals(1, fib(1))
        assertEquals(2, fib(2))
        assertEquals(3, fib(3))
        assertEquals(5, fib(4))
        assertEquals(8, fib(5))
        assertEquals(13, fib(6))
        assertEquals(21, fib(7))
        assertEquals(34, fib(8))
        assertEquals(55, fib(9))
        assertEquals(89, fib(10))
    }

    private fun fuzzBuzzPrinted(): List<String> {
        val printedLines = mutableListOf<String>()
        mockkStatic("kotlin.io.ConsoleKt")
        val console = mockk<Console>()
        every { console.println(any()) } answers { printedLines += firstArg<Any?>().toString() }
        fizzBuzz(console)
        clearAllMocks()
        return printedLines
    }
}