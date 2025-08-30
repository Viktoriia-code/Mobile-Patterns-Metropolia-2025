package org.example

class Fraction(numerator: Int, denominator: Int, private val sign: Int = 1): Comparable<Fraction> {
    private val numerator: Int
    private val denominator: Int

    init {
        if (denominator == 0) throw IllegalArgumentException("Denominator cannot be zero")

        var num = numerator
        var den = denominator

        val g = gcd(kotlin.math.abs(num), den)
        num /= g
        den /= g

        this.numerator = num
        this.denominator = den
    }

    private fun gcd(a: Int, b: Int): Int {
        return if (b == 0) a else gcd(b, a % b)
    }

    override fun toString(): String {
        val num = sign * numerator
        return "$num/$denominator"
    }

    override fun compareTo(other: Fraction): Int {
        val left = sign * numerator * other.denominator
        val right = other.sign * other.numerator * denominator
        return left.compareTo(right)
    }

    fun add(other: Fraction): Fraction {
        val commonDen = denominator * other.denominator
        val num1 = sign * numerator * other.denominator
        val num2 = other.sign * other.numerator * denominator
        return Fraction(num1 + num2, commonDen)
    }

    fun substr(other: Fraction): Fraction {
        return add(other.negate())
    }

    fun mult(fraction: Fraction): Fraction {
        val newSign = this.sign * fraction.sign
        val newNum = this.numerator * fraction.numerator
        val newDen = this.denominator * fraction.denominator
        return Fraction(newNum, newDen, newSign)
    }

    fun negate(): Fraction {
        return Fraction(numerator, denominator, -sign)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Fraction) return false
        return this.sign * this.numerator == other.sign * other.numerator &&
                this.denominator == other.denominator
    }

    operator fun plus(fraction: Fraction): Fraction = add(fraction)
    operator fun minus(fraction: Fraction): Fraction = add(fraction.negate())
    operator fun unaryMinus(): Fraction = negate()
    operator fun div(fraction: Fraction): Fraction {
        return mult(Fraction(fraction.denominator, fraction.numerator, fraction.sign))
    }
    operator fun times(fraction: Fraction): Fraction = mult(fraction)
}