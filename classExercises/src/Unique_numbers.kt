fun main() {
    val numbers: MutableList<Int> = mutableListOf()

    while (numbers.size < 7) {
        val num = (1..40).random()
        if (num !in numbers) {
            numbers.add(num)
        }
    }

    println("Unique numbers: ${numbers.sorted().joinToString(", ")}")

    // One-liner alternative:
    // val numbers = (1..40).shuffled().take(7).sorted()
}