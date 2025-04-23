package lotto.error

import lotto.policy.GamePolicy

object ErrorTemplate {
	fun numberCount(expected: Int): String =
		"${GamePolicy.ERROR_PREFIX} lotto numbers must contain exactly $expected numbers."

	fun numberRange(label: String, min: Int, max: Int): String =
		"${GamePolicy.ERROR_PREFIX} $label must be between $min and $max."

	fun duplicate(label: String): String =
		"${GamePolicy.ERROR_PREFIX} $label must not be duplicated."

	fun notDivisibleBy(label: String, divisor: Int): String =
		"${GamePolicy.ERROR_PREFIX} $label must be divisible by $divisor."

	fun invalidPurchaseAmount(ticketPrice: Int) =
		"${GamePolicy.ERROR_PREFIX} purchase amount must be greater than or equal to $ticketPrice."
}