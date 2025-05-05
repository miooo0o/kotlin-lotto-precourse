package lotto

class Lotto(private val numbers: List<Int>) {
	init {
		require(numbers.size == 6) { "[ERROR] lotto should have 6 numbers" }
	}

	override fun toString(): String {
		return numbers.toString()
	}

	fun countMatch(winningNumbers: List<Int>): Int {
		return numbers.count { it in winningNumbers }
	}

	fun hasBonus(bonusNumber: Int): Boolean {
		return numbers.any { it == bonusNumber }
	}
}