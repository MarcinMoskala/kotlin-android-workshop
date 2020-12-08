package com.workshop.universityannouncementsboard.extra.coroutineexamples

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Test
import java.util.concurrent.atomic.AtomicInteger

@Suppress("FunctionName")
class FlowKatasTests {

    @Test()
    fun producingUnitsTests() = runBlockingTest {
        kotlin.test.assertEquals(listOf(), producingUnits(0).toList())
        kotlin.test.assertEquals(listOf(Unit), producingUnits(1).toList())
        kotlin.test.assertEquals(listOf(Unit, Unit), producingUnits(2).toList())
        kotlin.test.assertEquals(listOf(Unit, Unit, Unit), producingUnits(3).toList())
        for (i in 1..100 step 7) {
            kotlin.test.assertEquals(List(i) { Unit }, producingUnits(i).toList())
        }
    }

    @Test()
    fun toToggleTests() = runBlockingTest {
        kotlin.test.assertEquals(listOf(), producingUnits(0).toToggle().toList())
        kotlin.test.assertEquals(listOf(true), producingUnits(1).toToggle().toList())
        kotlin.test.assertEquals(listOf(true, false), producingUnits(2).toToggle().toList())
        kotlin.test.assertEquals(listOf(true, false, true), producingUnits(3).toToggle().toList())
        kotlin.test.assertEquals(listOf(true, false, true, false), producingUnits(4).toToggle().toList())
    }

    @Test()
    fun toNextNumbersTests() = runBlockingTest {
        kotlin.test.assertEquals(listOf(), producingUnits(0).toNextNumbers().toList())
        kotlin.test.assertEquals(listOf(1), producingUnits(1).toNextNumbers().toList())
        kotlin.test.assertEquals(listOf(1, 2), producingUnits(2).toNextNumbers().toList())
        kotlin.test.assertEquals(listOf(1, 2, 3), producingUnits(3).toNextNumbers().toList())
        for (i in 1..100 step 7) {
            val list = List(i) { it + 1 }
            kotlin.test.assertEquals(list, list.map { Unit }.asFlow().toNextNumbers().toList())
        }
    }

    @Test()
    fun withHistoryTests() = runBlockingTest {
        kotlin.test.assertEquals(listOf(listOf()), producingUnits(0).withHistory().toList())
        kotlin.test.assertEquals(listOf(listOf(), listOf(Unit)), producingUnits(1).withHistory().toList())
        kotlin.test.assertEquals(listOf(listOf(), listOf(Unit), listOf(Unit, Unit)), producingUnits(2).withHistory().toList())

        kotlin.test.assertEquals(listOf(listOf(), listOf(1), listOf(1, 2)), producingUnits(2).toNextNumbers().withHistory().toList())
        kotlin.test.assertEquals(listOf(listOf(), listOf(true), listOf(true, false)), producingUnits(2).toToggle().withHistory().toList())

        val flow = flow {
            emit("A")
            delay(100)
            emit(10)
            emit("C")
        }
        kotlin.test.assertEquals(listOf(listOf(), listOf("A"), listOf("A", 10), listOf("A", 10, "C")), flow.withHistory().toList())
    }

    @Test()
    fun flowDelayEachTests() = runBlockingTest {
        val emittedNum = AtomicInteger()

        producingUnits(100)
            .delayEach(1000)
            .onEach { emittedNum.incrementAndGet() }
            .launchIn(this)

        kotlin.test.assertEquals(0, emittedNum.get())

        // After 1 500ms there should be one element
        delay(1_500)
        kotlin.test.assertEquals(1, emittedNum.get())

        // After another 2 000ms there should be two more elements
        delay(2_000)
        kotlin.test.assertEquals(3, emittedNum.get())

        // After another 12 000ms there should be twelve more elements
        delay(12_000)
        kotlin.test.assertEquals(15, emittedNum.get())
    }

    @Test()
    fun makeTimerTests() = runBlockingTest {
        val mutex = Mutex()
        var ticked = listOf<Int>()
        makeTimer(1000, 10, 20)
            .onEach {
                mutex.withLock { ticked += it }
            }
            .launchIn(this)

        kotlin.test.assertEquals(listOf(10), mutex.withLock { ticked })

        // After 1 500ms there should be one element
        delay(1_500)
        kotlin.test.assertEquals(listOf(10, 11), mutex.withLock { ticked })

        // After another 2 000ms there should be two more elements
        delay(2_000)
        kotlin.test.assertEquals(listOf(10, 11, 12, 13), mutex.withLock { ticked })

        // After another 12 000ms there should be twelve more elements
        delay(12_000)
        kotlin.test.assertEquals((10..20).toList(), mutex.withLock { ticked })
    }

    @Test()
    fun `makeTimer if delayed in between, do not provide old values but only shows the last one`() = runBlockingTest {
        val maxValue = 20
        val res = makeTimer(100, 1, maxValue)
            .onEach {
                if (it == 1) delay(50) // To make it clearly after timer delay
                // We don't need to check more often than every 0.5s
                delay(500)
            }
            .toList()

        kotlin.test.assertEquals(listOf(1, 6, 11, 16, 20), res)
    }

    @Test()
    fun makeLightSwitchTests() = runBlockingTest {
        val switchOne = flow<Boolean> {
            emit(true)
            delay(1000)
            emit(false)
            delay(10)
            emit(true)
            delay(500) // 1500
            emit(false)
        }
        val switchTwo = flow<Boolean> {
            emit(false)
            delay(200)
            emit(true)
            delay(1000) // 1200
            emit(false)
        }

        var lightOn = false
        launch {
            makeLightSwitch(switchOne, switchTwo).collect {
                lightOn = it
            }
        }

        delay(50)
        kotlin.test.assertEquals(true, lightOn)
        delay(200) // 250
        kotlin.test.assertEquals(false, lightOn)
        delay(800) // 1050
        kotlin.test.assertEquals(false, lightOn)
        delay(200) // 1250
        kotlin.test.assertEquals(true, lightOn)
        delay(300) // 1550
        kotlin.test.assertEquals(false, lightOn)
    }

    @Test()
    fun makeLightSwitchToggleTests() = runBlockingTest {
        val switchOne = flow<Unit> {
            emit(Unit)
            delay(1000)
            emit(Unit)
            delay(10)
            emit(Unit)
            delay(500) // 1500
            emit(Unit)
        }
        val switchTwo = flow<Unit> {
            emit(Unit)
            delay(200)
            emit(Unit)
            delay(1000) // 1200
            emit(Unit)
        }

        var lightOn = false
        launch {
            makeLightSwitchToggle(switchOne, switchTwo).collect {
                lightOn = it
            }
        }

        delay(50)
        kotlin.test.assertEquals(true, lightOn)
        delay(200) // 250
        kotlin.test.assertEquals(false, lightOn)
        delay(800) // 1050
        kotlin.test.assertEquals(false, lightOn)
        delay(200) // 1250
        kotlin.test.assertEquals(true, lightOn)
        delay(300) // 1550
        kotlin.test.assertEquals(false, lightOn)
    }

    @Test()
    fun polonaisePairingTests() = runBlockingTest {
        val track1 = flow<Dancer> {
            emit(Dancer("A"))
            emit(Dancer("B"))
            delay(1000)
            emit(Dancer("C"))
            emit(Dancer("D"))
        }
        val track2 = flow<Dancer> {
            emit(Dancer("1"))
            delay(600)
            emit(Dancer("2"))
            delay(1000)
            emit(Dancer("3"))
        }

        val res = polonaisePairing(track1, track2).toList()
        val expected = listOf("A" to "1", "B" to "2", "C" to "3").map { Dancer(it.first) to Dancer(it.second) }
        kotlin.test.assertEquals(expected, res)

        var lastPair: Pair<Dancer, Dancer>? = null
        launch {
            polonaisePairing(track1, track2).collect { lastPair = it }
        }

        kotlin.test.assertEquals(Dancer("A") to Dancer("1"), lastPair)
        delay(200) // 200
        kotlin.test.assertEquals(Dancer("A") to Dancer("1"), lastPair)

        delay(500) // 700
        kotlin.test.assertEquals(Dancer("B") to Dancer("2"), lastPair)
        delay(500) // 1200
        kotlin.test.assertEquals(Dancer("B") to Dancer("2"), lastPair)

        delay(500) // 1700
        kotlin.test.assertEquals(Dancer("C") to Dancer("3"), lastPair)
    }
}