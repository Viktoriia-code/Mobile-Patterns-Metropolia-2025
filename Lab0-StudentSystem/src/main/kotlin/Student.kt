package org.example

class Student(name: String, age: Int) : Human(name, age) {
    private val courseList = mutableListOf<CourseRecord>()

    val courses: List<CourseRecord>
        get() = courseList.toList()

    fun addCourse(course: CourseRecord) {
        courseList.add(course)
    }

    fun weightedAverage(): Double {
        if (courseList.isEmpty()) return 0.0
        val totalCredits = courseList.sumOf { it.credits }
        val weightedSum = courseList.sumOf { it.grade * it.credits }
        return weightedSum / totalCredits
    }

    fun minMaxGrades(): Pair<Double?, Double?> {
        if (courseList.isEmpty()) return Pair(null, null)
        val grades = courseList.map { it.grade }
        return Pair(grades.minOrNull(), grades.maxOrNull())
    }
}