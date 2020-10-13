package examples

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    val channel = Channel<String>(Channel.CONFLATED)

    launch {
        var i = 1
        repeat(5) {
            channel.send("Ping ${i++}")
            println("Message sent")
        }
    }

    // Listener
    delay(10)
    for (text in channel) {
        delay(1000)
        println(text)
    }
}