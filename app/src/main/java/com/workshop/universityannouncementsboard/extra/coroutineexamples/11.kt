package examples

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.coroutineContext

fun main() = runBlocking<Unit> {
    launch(CoroutineName("AAA")) { a() }
    launch(CoroutineName("BBB")) { a() }
}

suspend fun a() {
    print(coroutineContext[CoroutineName]?.name)
}