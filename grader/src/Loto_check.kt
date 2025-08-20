fun main() {
    val nums = listOf(1,2,3,4,5,6,99)
    println("Checking lotto ticket: ${nums.sorted().joinToString(", ")}")
    if (nums.size < 7) {
        println("Not enough numbers.")
    } else if (nums.any { it !in 1..40 }) {
        println("Numbers out of range.")
    } else if (nums.toSet().size < 7) {
        println("Not all numbers are unique.")
    } else {
        print("The numbers is a valid lotto ticket.")
    }

    // Alternative solution using a single if statement:
    nums.size == 7 && nums.toSet().size == 7 && nums.all { it in 1..40 }
    val lotterynums = listOf(1, 2, 3, 4, 5, 6, 7)
    lotterynums.intersect(nums).size
}