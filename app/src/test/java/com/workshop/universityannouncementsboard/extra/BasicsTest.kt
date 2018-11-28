package com.workshop.universityannouncementsboard.extra

import org.junit.Assert.assertEquals
import org.junit.Test

class BasicsTest {

    @Test
    fun `FizzBuzz starts from 1 and 2`() {
        val solution = "1, 2, "
        assertEquals(solution, fizzBuzz().take(6))
    }

    @Test
    fun `Third FizzBuzz element is Fizz`() {
        assertEquals("Fizz", fizzBuzz().split(", ")[2])
    }

    @Test
    fun `Fifth FizzBuzz element is Buzz`() {
        assertEquals("Buzz", fizzBuzz().split(", ")[4])
    }

    @Test
    fun `Fifteenth FizzBuzz element is FizzBuzz`() {
        assertEquals("FizzBuzz", fizzBuzz().split(", ")[14])
    }

    @Test
    fun fizzBuzzTest() {
        val solution = "1, 2, Fizz, 4, Buzz, Fizz, 7, 8, Fizz, Buzz, 11, Fizz, 13, 14, FizzBuzz, 16, 17, Fizz, 19, Buzz, Fizz, 22, 23, Fizz, Buzz, 26, Fizz, 28, 29, FizzBuzz, 31, 32, Fizz, 34, Buzz, Fizz, 37, 38, Fizz, Buzz, 41, Fizz, 43, 44, FizzBuzz, 46, 47, Fizz, 49, Buzz, Fizz, 52, 53, Fizz, Buzz, 56, Fizz, 58, 59, FizzBuzz, 61, 62, Fizz, 64, Buzz, Fizz, 67, 68, Fizz, Buzz, 71, Fizz, 73, 74, FizzBuzz, 76, 77, Fizz, 79, Buzz, Fizz, 82, 83, Fizz, Buzz, 86, Fizz, 88, 89, FizzBuzz, 91, 92, Fizz, 94, Buzz, Fizz, 97, 98, Fizz, Buzz"
        assertEquals(solution, fizzBuzz())
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
}