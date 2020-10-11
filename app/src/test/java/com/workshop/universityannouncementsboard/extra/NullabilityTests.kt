package com.workshop.universityannouncementsboard.extra

import com.workshop.universityannouncementsboard.extra.nullability.*
import junit.framework.Assert.*
import org.junit.*

class NullabilityTests {

    class MailCollector(): Mailer {
        data class Mail(val email: String, val message: String)

        var emails = listOf<Mail>()

        override fun sendMessage(email: String, message: String) {
            emails += Mail(email, message)
        }
    }

    @Test
    fun `When client is null, email is not sent`() {
        val mailer = MailCollector()
        sendMessageToClient(null, "AAA", mailer)
        assertEquals(emptyList<MailCollector.Mail>(), mailer.emails)
    }

    @Test
    fun `When message is null, email is not sent`() {
        val mailer = MailCollector()
        sendMessageToClient(Client(PersonalInfo("AAA")), null, mailer)
        assertEquals(emptyList<MailCollector.Mail>(), mailer.emails)
    }

    @Test
    fun `When personal info is null, email is not sent`() {
        val mailer = MailCollector()
        sendMessageToClient(Client(null), "AAA", mailer)
        assertEquals(emptyList<MailCollector.Mail>(), mailer.emails)
    }

    @Test
    fun `When email is null, email is not sent`() {
        val mailer = MailCollector()
        sendMessageToClient(Client(PersonalInfo(null)), null, mailer)
        assertEquals(emptyList<MailCollector.Mail>(), mailer.emails)
    }

    @Test
    fun `Sends messages correctly`() {
        val mailer = MailCollector()
        sendMessageToClient(Client(PersonalInfo("AAA")), "BBB", mailer)
        assertEquals(listOf(MailCollector.Mail("AAA", "BBB")), mailer.emails)
    }
}