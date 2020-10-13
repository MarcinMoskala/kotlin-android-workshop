package examples

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    val channel = Channel<String>(Channel.UNLIMITED)

    launch {
        var i = 1
        repeat(5) {
            channel.send("Ping ${i++}")
            println("Message sent")
        }
        channel.close()
    }

    // Listener
    for (text in channel) {
        println(text)
        delay(1000)
    }
}