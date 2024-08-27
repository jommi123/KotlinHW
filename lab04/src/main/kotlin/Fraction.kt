import kotlin.math.abs

class Fraction(val numerator: Int, val denominator: Int, private val sign: Int = 1) : Comparable<Fraction> {

    override fun toString(): String {
        val fraction = reduce()
        val signString = if (fraction.sign == -1) "-" else ""
        return "$signString${fraction.numerator}/${fraction.denominator}"
    }


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

    operator fun div(fraction2: Fraction): Fraction {
        val newNumerator = numerator * fraction2.denominator
        val newDenominator = denominator * fraction2.numerator
        val newSign = sign * fraction2.sign
        return Fraction(newNumerator, newDenominator, newSign).reduce()
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

    operator fun minus(fraction2: Fraction): Fraction {
        return this.add(fraction2.negate())
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Fraction

        if (numerator != other.numerator) return false
        if (denominator != other.denominator) return false
        if (sign != other.sign) return false

        return true
    }

    override fun hashCode(): Int {
        var result = numerator
        result = 31 * result + denominator
        result = 31 * result + sign
        return result
    }
}


fun main() {
   println(Fraction(2,3) + Fraction(1,3))
}