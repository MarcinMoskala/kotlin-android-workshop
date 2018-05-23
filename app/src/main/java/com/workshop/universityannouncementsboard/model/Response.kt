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
}
 */
sealed class Response<R, E>
class Success<R, E>(val value: R): Response<R, E>()
class ErrorResponse<R, E>(val error: E): Response<R, E>()