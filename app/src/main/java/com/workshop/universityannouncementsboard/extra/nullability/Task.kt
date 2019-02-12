package com.workshop.universityannouncementsboard.extra.nullability

class Client(val personalInfo: PersonalInfo?)
class PersonalInfo(val email: String?)

interface Mailer {
    fun sendMessage(email: String, message: String)
}

/*
Rewrite Java method 'MessageUtil.sendMessageToClient' in Kotlin in 3 lines.
*/
fun sendMessageToClient(client: Client?, message: String?, mailer: Mailer) {
    TODO()
}