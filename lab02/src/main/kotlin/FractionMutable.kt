import kotlin.math.abs

class FractionMutable(var numerator: Int, var denominator: Int, var sign: Int = 1) {


    override fun toString(): String {
        reduce()
        val signString = if (sign == -1) "-" else ""
        return "$signString$numerator/$denominator"
    }

    private fun greatestCommonDivisor(a: Int, b: Int): Int {
        return if (b==0) a else greatestCommonDivisor(b, a % b)
    }

    private fun reduce() {
        val gcd = greatestCommonDivisor(numerator, denominator)
        numerator /= gcd
        denominator /= gcd

    }

    fun negate() {
        sign *= -1
    }

    fun add(fraction2 : FractionMutable) {
        numerator = sign * numerator * fraction2.denominator + fraction2.sign * denominator * fraction2.numerator
        denominator *= fraction2.denominator
        sign = if (numerator < 0) -1 else 1
        numerator = abs(numerator)

        reduce()
    }

    fun mult(f2: FractionMutable) {
        numerator *= f2.numerator
        denominator *= f2.denominator
        sign *= f2.sign
        reduce()
    }

    fun div(f2: FractionMutable) {
        numerator *= f2.denominator
        denominator *= f2.numerator
        sign *= f2.sign
        reduce()
    }

    fun intPart(): Int {
        return sign * (numerator / denominator)
    }



}

fun main() {
    val a = FractionMutable(8, 3)
    a.div(FractionMutable(4, 6))
    println(a)
}