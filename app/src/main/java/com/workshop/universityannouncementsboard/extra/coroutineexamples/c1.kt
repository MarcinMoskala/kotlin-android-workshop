package examples.c1

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
fun main() = runBlocking {
    val channel = produce {
        repeat(5) { index ->
            send(index * 2)
            println("Producing next one")
            delay(1000)
        }
    }

    for (received in channel) {
        println(received)
    }
}