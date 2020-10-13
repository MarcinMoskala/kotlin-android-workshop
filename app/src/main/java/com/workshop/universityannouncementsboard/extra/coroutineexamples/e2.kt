package examples

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope

fun main() = runBlocking<Unit> {
    supervisorScope {
        launch {
            delay(1000)
            throw AssertionError("Cancelled")
        }
        launch {
            delay(2000)
            println("Done")
        }
    }
}
