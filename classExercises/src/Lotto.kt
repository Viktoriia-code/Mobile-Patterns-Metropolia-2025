class Lotto {
    private val correct: List<Int> = (1..40).shuffled().take(7)

    private fun isLegal(guess: List<Int>): Boolean =
        guess.toSet().size == 7 && guess.all { it in 1..40 }

    fun check(guess: List<Int>): Int? =
        if(isLegal(guess)) correct.intersect(guess.toSet()).size else null
}

fun main() {
    val lottoGame = Lotto()

    val guess = listOf(5, 11, 23, 34, 36, 38, 40)
    println("$guess: ${lottoGame.check(guess)}")
}