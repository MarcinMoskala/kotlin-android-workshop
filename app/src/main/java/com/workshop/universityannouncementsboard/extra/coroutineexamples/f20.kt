package examples

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

//inline fun <T, R> Flow<T>.map(crossinline transform: suspend (value: T) -> R): Flow<R> = transform { value ->
//    emit(transform(value))
//}