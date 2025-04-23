package lotto.error

import lotto.policy.LottoPolicy

object ErrorTemplate {
	fun numberCount(expected: Int): String =
		"${LottoPolicy.ERROR_PREFIX} lotto numbers must contain exactly $expected numbers."

	fun numberRange(label: String, min: Int, max: Int): String =
		"${LottoPolicy.ERROR_PREFIX} $label must be between $min and $max."

	fun duplicate(label: String): String =
		"${LottoPolicy.ERROR_PREFIX} $label must not be duplicated."

	fun notDivisibleBy(label: String, divisor: Int): String =
		"${LottoPolicy.ERROR_PREFIX} $label must be divisible by $divisor."

	fun invalidPurchaseAmount(ticketPrice: Int) =
		"${LottoPolicy.ERROR_PREFIX} purchase amount must be greater than or equal to $ticketPrice."
}