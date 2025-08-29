package org.example

class Major(val name: String) {
    private val studentList = mutableListOf<Student>()

    fun addStudent(student: Student) {
        studentList.add(student)
    }

    fun stats(): Triple<Double, Double, Double>? {
        val allGrades = studentList.flatMap { it.courses.map { course -> course.grade } }
        if (allGrades.isEmpty()) return null
        val min = allGrades.min()
        val max = allGrades.max()
        val avg = allGrades.average()
        return Triple(min, max, avg)
    }

    override fun toString(): String {
        return "Major(name='$name', students=${studentList.size})"
    }
}