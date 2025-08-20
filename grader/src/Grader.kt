import kotlin.math.round

fun main() {
    print("Exam points(0-100): ")
    var exam_points = readlnOrNull()?.toDoubleOrNull() ?: 0.0

    if (exam_points < 0.0) {
        exam_points = 0.0
    } else if (exam_points > 100.0) {
        exam_points = 100.0
    }

    print("Exercise points(0-100): ")
    var exercise_points = readlnOrNull()?.toDoubleOrNull() ?: 0.0

    if (exercise_points < 0.0) {
        exercise_points = 0.0
    } else if (exercise_points > 100.0) {
        exercise_points = 100.0
    }

    val total_points = 0.75*exam_points + 0.25*exercise_points

    val grade = if (exam_points < 40.0) {
        0.0
    } else {
        val raw = 0.5 + (total_points - 40.0) * (5.49 - 0.5) / (100.0 - 40.0)
        round(raw).toInt()
    }
    println("Total points: $total_points")
    println("Grade: $grade")
}