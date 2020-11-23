package examples

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

fun main() = runBlocking<Unit> {
    val context: CoroutineContext = CoroutineName("AAA") + CoroutineName("BBB") + Job() + CoroutineExceptionHandler({ coroutineContext, throwable -> })
    val comp: CoroutineName.Key = CoroutineName
    val nameCtx = context[comp]
    print(nameCtx?.name)

}