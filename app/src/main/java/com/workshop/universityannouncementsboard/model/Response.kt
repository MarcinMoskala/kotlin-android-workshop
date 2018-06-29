package com.workshop.universityannouncementsboard.model

/* TODO:
   I would like to use it this way:

fun main(args: Array<String>) {
    val rs1 = Success(1)
    val re1 = ErrorResponse(Error())
    val re2 = ErrorResponse("Error")

    val r1: Response<Int, Error> = rs1
    val r2: Response<Int, Error> = re1

    val r3: Response<Int, String> = rs1
    val r4: Response<Int, String> = re2

    val r5: Response<Any, Throwable> = rs1
    val r6: Response<Any, Throwable> = re1

    val s = Success(String())
    val s1: Success<CharSequence> = s
    val s2: Success<Any> = s

    val e = ErrorResponse(Error())
    val e1: ErrorResponse<Throwable> = e
    val e2: ErrorResponse<Any> = e
}
 */
sealed class Response<R, E>
class Success<R, E>(val value: R): Response<R, E>()
class ErrorResponse<R, E>(val error: E): Response<R, E>()