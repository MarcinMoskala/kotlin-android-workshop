package com.workshop.universityannouncementsboard.extra.nullability

import kotlin.contracts.*

class Client(val personalInfo: PersonalInfo?)
class PersonalInfo(val email: String?)

interface Mailer {
    fun sendMessage(email: String, message: String)
}

/*
Rewrite Java method 'MessageUtil.sendMessageToClient' in Kotlin in 3 lines.
*/
fun sendMessageToClient(client: Client?, message: String?, mailer: Mailer) {
    message ?: return
    val email = client?.personalInfo?.email ?: return
    mailer.sendMessage(email, message)
}

inline fun <R> run(block: () -> R): R = block()