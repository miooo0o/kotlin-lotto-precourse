package lotto.view.input

import lotto.error.Common
import lotto.error.ErrorType
import lotto.policy.isAtLeastTicketPrice
import lotto.policy.isDivisibleByTicketPrice

fun parseAmount(input: String): Long? {
	return input.toLongOrNull()
}

fun validateAmount(amount: Long): ErrorType {
	return listOf(
		amount.isAtLeastTicketPrice(),
		amount.isDivisibleByTicketPrice()
	).firstOrNull() ?: Common.NON_ERROR
}