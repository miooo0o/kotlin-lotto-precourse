package lotto.domain

import lotto.error.WinningError
import lotto.error.isStatusSuccess
import lotto.error.toMessage
import lotto.policy.hasNoDuplicates
import lotto.policy.hasValidSize
import lotto.policy.isInRange

class Lotto(private val numbers: List<Int>) {
	init {
		numbers.validateSelf()
	}

	fun display() {
		println(numbers.joinToString(prefix = "[", postfix = "]"))
	}
}

private fun List<Int>.validateSelf() {
	require(hasValidSize().isStatusSuccess()) { WinningError.INVALID_SIZE.toMessage() }
	require(hasNoDuplicates().isStatusSuccess()) { WinningError.DUPLICATE_NUMBER.toMessage() }
	require(isInRange().isStatusSuccess()) { WinningError.NOT_IN_RANGE.toMessage() }
}
