import kotlin.math.abs

class Fraction(val numerator: Int, val denominator: Int, private val sign: Int = 1) : Comparable<Fraction> {

    private fun greatestCommonDivisor(a: Int, b: Int): Int {
        return if (b == 0) a else greatestCommonDivisor(b, a % b)
    }

    private fun reduce(): Fraction {
        val gcd = greatestCommonDivisor(numerator, denominator)
        return Fraction(numerator / gcd, denominator / gcd, sign)
    }

    fun negate(): Fraction {
        return Fraction(numerator, denominator, -sign)
    }

    fun add(fraction2: Fraction): Fraction {
        val newNumerator = sign * numerator * fraction2.denominator + fraction2.sign * denominator * fraction2.numerator
        val newDenominator = denominator * fraction2.denominator
        val newSign = if (newNumerator < 0) -1 else 1
        return Fraction(abs(newNumerator), newDenominator, newSign).reduce()
    }

    fun mult(fraction2: Fraction): Fraction {
        val newNumerator = numerator * fraction2.numerator
        val newDenominator = denominator * fraction2.denominator
        val newSign = sign * fraction2.sign
        return Fraction(newNumerator, newDenominator, newSign).reduce()
    }

    fun div(fraction2: Fraction): Fraction {
        val newNumerator = numerator * fraction2.denominator
        val newDenominator = denominator * fraction2.numerator
        val newSign = sign * fraction2.sign
        return Fraction(newNumerator, newDenominator, newSign).reduce()
    }

    override fun toString(): String {
        val signString = if (sign == -1) "-" else ""
        return "$signString$numerator/$denominator"
    }

    fun intPart(): Int {
        return sign * (numerator / denominator)
    }

    override fun compareTo(other: Fraction): Int {
        val value1 = sign * numerator * other.denominator
        val value2 = other.sign * other.numerator * denominator
        return value1.compareTo(value2)
    }

    operator fun plus(fraction2: Fraction): Fraction {
        return this.add(fraction2)
    }

    operator fun times(fraction2: Fraction): Fraction {
        return this.mult(fraction2)
    }

    operator fun unaryMinus(): Fraction {
        return this.negate()
    }
}


fun main() {
    val a = Fraction(1, 2, -1)
    println(a)
    println(a.add(Fraction(1,3)))
    println(a.mult(Fraction(5,2, -1)))
    println(a.div(Fraction(2,1)))
    println(-Fraction(1,6) + Fraction(1,2))
    println(Fraction(2,3) * Fraction(3,2))
    println(Fraction(1,2) > Fraction(2,3))
}