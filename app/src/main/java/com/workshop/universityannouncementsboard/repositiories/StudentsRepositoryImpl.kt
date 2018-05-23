package com.workshop.universityannouncementsboard.repositiories

import com.workshop.universityannouncementsboard.model.Student

class StudentsRepositoryImpl : StudentsRepository {
    override fun getStudents(): List<Student> = listOf(
            Student("Marc", "Smith", 87.0, 32),
            Student("Marc", "Smith", 37.0, 32),
            Student("Marc", "Smith", 87.0, 12),
            Student("Peter", "Jackson", 21.0, 24),
            Student("Noely", "Peterson", 91.0, 22),
            Student("Michael", "Angelo", 71.0, 12),
            Student("Noe", "Samson", 41.0, 18),
            Student("Timothy", "Johnson", 51.0, 15),
            Student("Noely", "Peterson", 91.0, 22),
            Student("Ester", "Adams", 81.0, 30),
            Student("Dior", "Angel", 88.5, 38),
            Student("Naja", "Marcson", 100.0, 31),
            Student("Oregon", "Dart", 85.5, 30),
            Student("Ron", "Peters", 89.0, 31),
            Student("Harry", "Potter", 80.0, 30),
            Student("Sansa", "Stark", 49.5, 14),
            Student("Jamme", "Lannister", 80.0, 30),
            Student("Alex", "Nolan", 86.0, 33),
            Student("Jon", "Johnson", 80.1, 30),
            Student("James", "Johnson", 80.2, 30),
            Student("Jack", "Johnson", 80.3, 30)
    )
}