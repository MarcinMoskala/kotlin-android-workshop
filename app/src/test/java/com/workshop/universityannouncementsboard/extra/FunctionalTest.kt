package com.workshop.universityannouncementsboard.extra

import io.mockk.*
import org.junit.Assert.assertEquals
import org.junit.Test

class FunctionalTest {

    @Test
    fun testLambdaFunctionalTypeSpecified() {
        testFunctions(LambdaFunctionalTypeSpecified())
    }

    @Test
    fun testLambdaFunctionalTypeInferred() {
        testFunctions(LambdaFunctionalTypeInferred())
    }

    @Test
    fun testAnonymousFunctionalTypeSpecified() {
        testFunctions(AnonymousFunctionalTypeSpecified())
    }

    @Test
    fun testAnonymousFunctionalTypeInferred() {
        testFunctions(AnonymousFunctionalTypeInferred())
    }

    @Test
    fun testFunctionReferenceFunctionalTypeInferred() {
        testFunctions(FunctionReferenceFunctionalTypeInferred())
    }

    @Test
    fun testBoundedFunctionReferenceFunctionalTypeInferred() {
        testFunctions(BoundedFunctionReferenceFunctionalTypeInferred())
    }

    fun testFunctions(obj: FunctionsFunctional) {
        assertEquals(3, obj.add(1, 2))
        assertEquals(6, obj.triple(2))
        assertEquals("BBB", obj.longestOf("AA", "BBB", "CC"))
        assertEquals("AA", obj.longestOf("AA", "B", "C"))
    }
}