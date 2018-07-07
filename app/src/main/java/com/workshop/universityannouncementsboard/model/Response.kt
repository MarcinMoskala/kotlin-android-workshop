package com.workshop.universityannouncementsboard.model

sealed class Response<out R, out E>
class Success<out R>(val value: R) : Response<R, Nothing>()
class ErrorResponse<out E>(val error: E) : Response<Nothing, E>()