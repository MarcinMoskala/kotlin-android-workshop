package com.workshop.universityannouncementsboard

import com.workshop.universityannouncementsboard.model.Student

val internshipStudent = Student("Marc", "Smith", 87.0, 32)
val studentWithTooLowResultToInternship = Student("Marcus", "Smith", 37.0, 32)
val studentWithNotEnoughPointsForInternship = Student("Marcello", "Smith", 87.0, 12)
val studentNotPassingBecauseOfResult = Student("Peter", "Jackson", 21.0, 24)
val studentNotPassingBecauseOfPoints = Student("Michael", "Angelo", 71.0, 12)

val aStudent1 = Student("Noe", "Samson", 41.0, 18)
val aStudent2 = Student("Timothy", "Johnson", 51.0, 15)
val aStudent3 = Student("Noe", "Peterson", 91.0, 22)
val aStudent4 = Student("Ester", "Adams", 81.0, 30)

val students = listOf(
        internshipStudent,
        studentWithTooLowResultToInternship,
        studentWithNotEnoughPointsForInternship,
        studentNotPassingBecauseOfResult,
        Student("Noely", "Peterson", 91.0, 22),
        studentNotPassingBecauseOfPoints,
        aStudent1,
        aStudent2,
        aStudent3,
        aStudent4,
        Student("Dior", "Angel", 88.5, 38),
        Student("Naja", "Marcson", 100.0, 31),
        Student("Oregon", "Dart", 85.5, 30),
        Student("Ron", "Peters", 89.0, 31),
        Student("Harry", "Potter", 80.0, 30),
        Student("Sansa", "Stark", 49.5, 14),
        Student("Jamme", "Lannister", 80.0, 30),
        Student("Alex", "Nolan", 86.0, 33),
        Student("Jon", "Johnson", 85.1, 31),
        Student("James", "Johnson", 85.2, 31),
        Student("Jack", "Johnson", 85.3, 31)
)