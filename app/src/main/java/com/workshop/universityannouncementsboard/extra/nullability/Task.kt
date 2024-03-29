package com.workshop.universityannouncementsboard.extra.nullability

class Client(val personalInfo: PersonalInfo?)
class PersonalInfo(val email: String?)

interface Mailer {
    fun sendMessage(email: String, message: String)
}

// 1. Use Elvis operator for unpacking both values
// 2. Use Elvis operator for unpacking email and smart-casting message
// 3. Use Elvis operator smart-casting email and message
// 4. Use if for smart-casting email and message
// 5. Use if with return for smart-casting email and message
// Optional: 6. Use let for email and message

/*
Rewrite Java method 'MessageUtil.sendMessageToClient' in Kotlin in 3 lines.
*/
fun sendMessageToClient(client: Client?, message: String?, mailer: Mailer) {

}