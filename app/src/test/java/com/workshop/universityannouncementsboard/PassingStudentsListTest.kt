package com.workshop.universityannouncementsboard

import com.workshop.universityannouncementsboard.repositiories.AnnouncementsRepositoryImpl
import com.workshop.universityannouncementsboard.repositiories.StudentsRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class PassingStudentsListTest {

    @Test
    fun `Single student that matches criteria is displayed`() {
        val repo: StudentsRepository = mockk()
        every { repo.getStudents() } returns listOf(internshipStudent)
        val annRepo = AnnouncementsRepositoryImpl(repo)

        // When
        val text = annRepo.makePassingStudentsListText()

        // Then
        val expexted = "Marc Smith, 87.0"
        assertEquals(expexted, text)
    }

    @Test
    fun `Single student with too low result doesn't get internship`() {
        val repo: StudentsRepository = mockk()
        every { repo.getStudents() } returns listOf(studentNotPassingBecauseOfResult)
        val annRepo = AnnouncementsRepositoryImpl(repo)

        // When
        val text = annRepo.makePassingStudentsListText()

        // Then
        assertEquals("", text)
    }

    @Test
    fun `Single student with not enough doesn't get internship`() {
        val repo: StudentsRepository = mockk()
        every { repo.getStudents() } returns listOf(studentNotPassingBecauseOfPoints)
        val annRepo = AnnouncementsRepositoryImpl(repo)

        // When
        val text = annRepo.makePassingStudentsListText()

        // Then
        assertEquals("", text)
    }

    @Test
    fun `Complex test`() {
        val repo: StudentsRepository = mockk()
        every { repo.getStudents() } returns students
        val annRepo = AnnouncementsRepositoryImpl(repo)

        // When
        val text = annRepo.makePassingStudentsListText()

        // Then
        val expected = """
            Ester Adams, 81.0
            Dior Angel, 88.5
            Oregon Dart, 85.5
            Jack Johnson, 80.3
            James Johnson, 80.2
            Jon Johnson, 80.1
            Jamme Lannister, 80.0
            Naja Marcson, 100.0
            Alex Nolan, 86.0
            Ron Peters, 89.0
            Noely Peterson, 91.0
            Noely Peterson, 91.0
            Harry Potter, 80.0
            Marc Smith, 87.0
        """.trimIndent()
        assertEquals(expected, text)
    }

}