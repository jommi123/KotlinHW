class Lotto {

    private val lottoRange = 1..40
    private val n = 7
    private var secretNumbers = listOf(4, 7, 8, 10, 15, 2, 3)

    fun pickNDistinct(range: IntRange, n: Int): List<Int> {
        if (n <= range.count()) {
            return range.shuffled().take(n)
        }
        return emptyList()
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
                if (i in range) {
                    return true
                }
            }
        }
        return false
    }

    fun checkGuess(guess: List<Int>, secret: List<Int> = secretNumbers): Int {
        if (isLegalLottoGuess(guess)) {
            return numCommon(guess, secret)
        }
        return 0
    }

    fun readNDistinct(low: Int, high: Int, n: Int): List<Int> {
        val guess = readlnOrNull()

        if (guess != null) {
            val list: List<Int> = guess.split(",").map { it.toInt() }

            if (isLegalLottoGuess(list, low..high, n)) {
                return list
            }

        }

        return emptyList()
    }

    fun playLotto() {
        secretNumbers = pickNDistinct(1..40, 7)

        while (true) {
            println("Give 7 numbers from 1 to 40, separated by commas:")
            val guess = readNDistinct(1, 40, 7)

            if (guess.isEmpty()) {
                continue
            } else {
                println(checkGuess(guess, secretNumbers))
            }

            println("to continue enter 1 to stop enter anything else")
            val input = readlnOrNull()?.toInt()
            if (input == 1) {
                continue
            } else {
                break
            }


        }
    }

    fun findLotto(lotto: Lotto): Pair<Int, List<Int>> {
        var steps = 0

        var correctNumbers = pickNDistinct(1..40, 7)

        println("Give 7 numbers from 1 to 40, separated by commas")
        val guess = readNDistinct(1, 40, lotto.n)

        var correctCount = 0
        while (correctCount < 2) {
            correctNumbers = pickNDistinct(1..40, 7)
            steps++
            correctCount = lotto.checkGuess(guess, correctNumbers)

        }
        return Pair(steps, correctNumbers)


    }
}

fun main() {
    val lotto = Lotto()
    println(lotto.findLotto(lotto))


}