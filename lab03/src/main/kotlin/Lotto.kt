class Lotto {

    private val lottoRange = 1..40
    private val n = 7
    private val secretNumbers = listOf(4, 7, 8, 10, 15, 2, 3)

    fun pickNDistinct(range: IntRange, n: Int): List<Int> {
        val list = ArrayList<Int>()
        var number = n

        for (i in range) {
            if (number > 0) {
                list.add(i)
                number--
            }
        }
        return list
    }

    fun numDistinct(list: List<Int>): Int {
        return list.distinct().size
    }

    fun numCommon(list1: List<Int>, list2: List<Int>): Int {
        val distinctList1 = list1.distinct()
        val distinctList2 = list2.distinct()
        var number = 0

        for (i in distinctList1) {
            for (j in distinctList2) {
                if (i == j) {
                    number++
                }
            }
        }
        return number
    }

    fun isLegalLottoGuess(guess: List<Int>, range: IntRange = lottoRange, count: Int = n): Boolean {
        if (guess.distinct().size == count) {
            for (i in guess) {
                if (i !in range) {
                    return false
                }
            }
        }
        return true
    }

    fun checkGuess(guess: List<Int>, secret: List<Int> = secretNumbers): Int {
        if (isLegalLottoGuess(guess)) {
            return numCommon(guess, secret)
        }
        return 0
    }
//
//    fun readNDistinct(low: Int, high: Int, n: Int): List<Int> {
//
//    }

}

fun main() {
    val lotto = Lotto()
    var list = mutableListOf(1, 2, 1, 4, 5, 6, 7)
    var list2 = mutableListOf(1, 2, 3, 7, 40, 50, 60)


    println(lotto.pickNDistinct(2..6, 7))
    println(lotto.numDistinct(list))
    println(lotto.numCommon(list, list2))
    println(lotto.isLegalLottoGuess(list, 1..8, 7))
    println(lotto.checkGuess(list2))


}