package lotto.domain

import lotto.error.UnexpectedException
import lotto.error.isStatusFailure
import lotto.policy.GamePolicy
import lotto.policy.hasNoDuplicates
import lotto.policy.hasValidSize
import lotto.policy.isInRange

class Lotto(numbers: List<Int>) {
	private val ticketNumbers: List<Int>

	init {
		validateNumbers(numbers)
		ticketNumbers = numbers.toList().sorted()
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

	companion object {
		private fun validateNumbers(numbers: List<Int>) {
			listOf(
				numbers.hasValidSize(),
				numbers.hasNoDuplicates(),
				numbers.isInRange()
			).forEach { errorType ->
				if (errorType.isStatusFailure()) {
					throw UnexpectedException("${GamePolicy.ERROR_PREFIX} Lotto Validation failed")
				}
			}
		}
	}
}
