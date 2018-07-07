package com.workshop.universityannouncementsboard

import com.workshop.universityannouncementsboard.repositiories.AnnouncementsRepositoryImpl
import com.workshop.universityannouncementsboard.repositiories.StudentsRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class BestStudentsListTest {

    @Test
    fun `Single student that matches criteria gets biggest internship`() {
        val repo: StudentsRepository = mockk()
        every { repo.getStudents() } returns listOf(internshipStudent)
        val annRepo = AnnouncementsRepositoryImpl(repo)

        // When
        val text = annRepo.makeBestStudentsList()

        // Then
        val expected = "Marc Smith, 5000\$"
        assertEquals(expected, text)
    }

    @Test
    fun `Single student with too low result doesn't get internship`() {
        val repo: StudentsRepository = mockk()
        every { repo.getStudents() } returns listOf(studentWithTooLowResultToInternship)
        val annRepo = AnnouncementsRepositoryImpl(repo)

        // When
        val text = annRepo.makeBestStudentsList()

        // Then
        assertEquals("", text)
    }

    @Test
    fun `Single student with not enough doesn't get internship`() {
        val repo: StudentsRepository = mockk()
        every { repo.getStudents() } returns listOf(studentWithNotEnoughPointsForInternship)
        val annRepo = AnnouncementsRepositoryImpl(repo)

        // When
        val text = annRepo.makeBestStudentsList()

        // Then
        assertEquals("", text)
    }

    @Test
    fun `Complex test`() {
        val repo: StudentsRepository = mockk()
        every { repo.getStudents() } returns students
        val annRepo = AnnouncementsRepositoryImpl(repo)

        // When
        val text = annRepo.makeBestStudentsList()

        // Then
        val expected = """
            Ester Adams, 1000${'$'}
            Dior Angel, 3000${'$'}
            Oregon Dart, 1000${'$'}
            Jack Johnson, 1000${'$'}
            James Johnson, 1000${'$'}
            Jon Johnson, 1000${'$'}
            Naja Marcson, 5000${'$'}
            Alex Nolan, 1000${'$'}
            Ron Peters, 3000${'$'}
            Marc Smith, 3000${'$'}
        """.trimIndent()
        assertEquals(expected, text)
    }

}