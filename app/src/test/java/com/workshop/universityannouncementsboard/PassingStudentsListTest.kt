package com.workshop.universityannouncementsboard

import com.workshop.universityannouncementsboard.model.Student
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
        val expected = "Marc Smith, 87.0"
        assertEquals(expected, text)
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
    fun `Student needs more then 15 points be be accepted`() {
        val student = Student("Noely", "Peterson", 81.0, 15)
        val repo: StudentsRepository = mockk()
        every { repo.getStudents() } returns listOf(student)
        val annRepo = AnnouncementsRepositoryImpl(repo)

        // When
        val text = annRepo.makePassingStudentsListText()

        // Then
        assertEquals("", text)
    }

    @Test
    fun `Students are displayed in an alphanumerical order sorted by surname and then by name`() {
        val students = listOf(
            Student(name = "B", surname = "A", result = 81.0, pointsInSemester = 16),
            Student(name = "B", surname = "B", result = 82.0, pointsInSemester = 16),
            Student(name = "A", surname = "A", result = 83.0, pointsInSemester = 16),
            Student(name = "A", surname = "B", result = 84.0, pointsInSemester = 16)
        )
        val repo: StudentsRepository = mockk()
        every { repo.getStudents() } returns students
        val annRepo = AnnouncementsRepositoryImpl(repo)

        // When
        val text = annRepo.makePassingStudentsListText()

        // Then
        val expected = """
            A A, 83.0
            B A, 81.0
            A B, 84.0
            B B, 82.0
        """.trimIndent()
        assertEquals(expected, text)
    }

    @Test
    fun `result 50 points is acceptable`() {
        val student = Student("Noely", "Peterson", 50.0, 25)
        val repo: StudentsRepository = mockk()
        every { repo.getStudents() } returns listOf(student)
        val annRepo = AnnouncementsRepositoryImpl(repo)

        // When
        val text = annRepo.makePassingStudentsListText()

        // Then
        assertEquals("Noely Peterson, 50.0", text)
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
            Jack Johnson, 85.3
            James Johnson, 85.2
            Jon Johnson, 85.1
            Jamme Lannister, 80.0
            Naja Marcson, 100.0
            Alex Nolan, 86.0
            Ron Peters, 89.0
            Noe Peterson, 91.0
            Noely Peterson, 91.0
            Harry Potter, 80.0
            Marc Smith, 87.0
        """.trimIndent()
        assertEquals(expected, text)
    }

}