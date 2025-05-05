package lotto

import camp.nextstep.edu.missionutils.Console

interface ReadUntil<T> {
	fun ask()
	fun answer(): T
	fun validate(answered: T)
}

class WinningNumberUntil : ReadUntil<List<Int>> {
	override fun ask() {
		println("Please enter last week's winning numbers.")
	}

	override fun answer(): List<Int> {
		ask()
		val input = Console.readLine()?.split(",")?.mapNotNull { it.trim().toIntOrNull() }
		val nonNullInput = requireNotNull(input) { "input is null" }
		validate(nonNullInput)
		return nonNullInput
	}

	override fun validate(answered: List<Int>) {
		require(answered.isNotEmpty()) { "input is empty" }
		require(answered.size == 6) { "lotto numbers size should be 6" }
		require(answered.toSet().size == 6) { "duplicate number found" }
		require(answered.all { it in 1..45 }) { "not in the range" }
	}
}

class BonusUntil : ReadUntil<Int> {
	override fun ask() {
		println("Please enter the bonus number.")
	}

	override fun answer(): Int {
		ask()
		val input = Console.readLine()?.trim()?.toIntOrNull()
		val nonNullInput = requireNotNull(input) { "input is null" }
		validate(nonNullInput)
		return nonNullInput
	}

	override fun validate(answered: Int) {
		require(answered in 1..45) { "not in the range" }
	}

	fun validateDuplicate(bonus: Int, lottoNumbers: List<Int>) {
		require(bonus !in lottoNumbers) { "duplicate number found within winning numbers" }
	}
}


class AmountUntil : ReadUntil<Long> {
	override fun ask() {
		println("Please enter the purchase amount.")
	}

	override fun answer(): Long {
		ask()
		val input = Console.readLine()?.trim()?.toLongOrNull()
		val nonNullInput = requireNotNull(input) { "input is null" }
		validate(nonNullInput)
		return nonNullInput
	}

	override fun validate(answered: Long) {
		require(answered / 1000L > 0L && answered % 1000L == 0L)
	}
}