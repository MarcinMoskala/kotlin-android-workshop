package com.workshop.universityannouncementsboard.model

sealed class Response<R, E>
class Success<R, E>(val value: R) : Response<R, E>()
class ErrorResponse<R, E>(val error: E) : Response<R, E>()