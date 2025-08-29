package org.example

class FractionMutable(var numerator: Int, var denominator: Int, var sign: Int = 1) {
    init {
        if (denominator == 0) throw IllegalArgumentException("Denominator cannot be zero")
        if (numerator == 0) sign = 1
        if (denominator < 0) {
            denominator = -denominator
            sign = -sign
        }
        simplify()
    }

    private fun simplify() {
        val gcd = gcd(numerator, denominator)
        numerator /= gcd
        denominator /= gcd
    }

    private fun gcd(a: Int, b: Int): Int {
        return if (b == 0) a else gcd(b, a % b)
    }

    fun negate() {
        sign = -sign
    }

    fun add(other: FractionMutable) {
        val newNumerator = sign * numerator * other.denominator + other.sign * other.numerator * denominator
        val newDenominator = denominator * other.denominator
        numerator = kotlin.math.abs(newNumerator)
        denominator = newDenominator
        sign = if (newNumerator < 0) -1 else 1
        simplify()
    }

    fun mult(other: FractionMutable) {
        numerator *= other.numerator
        denominator *= other.denominator
        sign *= other.sign
        simplify()
    }

    fun div(other: FractionMutable) {
        if (other.numerator == 0) throw IllegalArgumentException("Cannot divide by zero")
        numerator *= other.denominator
        denominator *= other.numerator
        sign *= other.sign
        if (denominator < 0) {
            denominator = -denominator
            sign = -sign
        }
        simplify()
    }

    fun intPart(): Int {
        return (numerator / denominator) * sign
    }

    override fun toString(): String {
        val num = if (numerator == 0) 0 else sign * numerator
        return "$num/$denominator"
    }
}