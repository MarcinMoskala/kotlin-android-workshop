package com.workshop.universityannouncementsboard.extra.coroutineexamples

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.atomic.AtomicInteger

// Produces a flow of Unit
// For instance producingUnits(5) -> [Unit, Unit, Unit, Unit, Unit]
fun producingUnits(num: Int): Flow<Unit> = TODO()

// Adds a delay of time `timeMillis` between elements
fun <T> Flow<T>.delayEach(timeMillis: Long): Flow<T> = TODO()

// Should transform Unit's to toggled boolean value starting from true
// For instance flowOf(Unit, Unit, Unit, Unit).toNextNumbers() -> [true, false, true, false]
fun Flow<Unit>.toToggle(): Flow<Boolean> = TODO()

// Should transform Unit's to next numbers startling from 1
// For instance flowOf(Unit, Unit, Unit, Unit).toNextNumbers() -> [1, 2, 3, 4]
fun Flow<Unit>.toNextNumbers(): Flow<Int> = TODO()

// Produces not only elements, but the whole history till now
// For instance flowOf(1, "A", 'C').withHistory() -> [[], [1], [1, A], [1, A, C]]
fun <T> Flow<T>.withHistory(): Flow<List<T>> = TODO()

// Should create a flow that every `tickEveryMillis` should emit next numbers from `startNum` to `endNum`
fun makeTimer(tickEveryMillis: Long, startNum: Int, endNum: Int): Flow<Int> = TODO()

// Based on two light switches, should decide if the general light should be switched on.
// Should be if one is true and another is false
fun makeLightSwitch(switch1: Flow<Boolean>, switch2: Flow<Boolean>): Flow<Boolean> = TODO()

// Based on two light switches, should decide if the general light should be switched on.
// Should be if one is turned on and another is off
// At the beginning, both switches are off, and each action toggles
fun makeLightSwitchToggle(switch1: Flow<Unit>, switch2: Flow<Unit>): Flow<Boolean> = TODO()

fun polonaisePairing(track1: Flow<Dancer>, track2: Flow<Dancer>): Flow<Pair<Dancer, Dancer>> = TODO()

data class Dancer(val name: String)