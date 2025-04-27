package lotto.domain

import lotto.error.WinningError
import lotto.error.isStatusSuccess
import lotto.error.toMessage
import lotto.policy.hasNoDuplicates
import lotto.policy.hasValidSize
import lotto.policy.isInRange

class Lotto(numbers: List<Int>) {
	private val ticketNumbers: List<Int> = numbers.sorted()

	init {
		this.ticketNumbers.validateSelf()
	}

	fun display() {
		println(ticketNumbers.joinToString(prefix = "[", postfix = "]"))
	}

	fun countMatched(winningNumbers: List<Int>): Int {
		return ticketNumbers.count { it in winningNumbers }
	}

	fun contains(bonusNumber: Int): Boolean {
		return ticketNumbers.contains(bonusNumber)
	}
}

private fun List<Int>.validateSelf() {
	require(hasValidSize().isStatusSuccess()) { WinningError.INVALID_SIZE.toMessage() }
	require(hasNoDuplicates().isStatusSuccess()) { WinningError.DUPLICATE_NUMBER.toMessage() }
	require(isInRange().isStatusSuccess()) { WinningError.NOT_IN_RANGE.toMessage() }
}
