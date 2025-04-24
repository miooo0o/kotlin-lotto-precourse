package lotto.validator

import lotto.error.*
import lotto.policy.GamePolicy

object Validator {
	fun checkWinningNumbers(winning: List<Int>): ErrorType {
		val sizeError = checkWinningSize(winning)
		if (sizeError.isStatusFailure()) return sizeError

		val duplicateError = checkWinningUniqueness(winning)
		if (duplicateError.isStatusFailure()) return duplicateError

		val rangeError = checkWinningRange(winning)
		if (rangeError.isStatusFailure()) return rangeError

		return Common.NON_ERROR
	}

	fun checkBonusNumber(bonus: Int, winning: List<Int>): ErrorType {
		val rangeError = checkBonusRange(bonus)
		if (rangeError.isStatusFailure()) return rangeError

		val duplicateError = checkBonusNotInWinning(bonus, winning)
		if (duplicateError.isStatusFailure()) return duplicateError

		return Common.NON_ERROR
	}

	fun checkAmount(amount: Long): ErrorType {
		val purchasableError = checkPurchasableAmount(amount)
		if (purchasableError.isStatusFailure()) return purchasableError
		return checkAmountDivisible(amount)
	}

	fun checkLongValue(num: Long?): ErrorType =
		ParseError.INVALID_RANGE.takeIf { num != null } ?: Common.NON_ERROR

	fun checkDigit(input: String): ErrorType =
		ParseError.INVALID_NUMBER_FORMAT.takeIf { input.all { it.isDigit() } } ?: Common.NON_ERROR

	fun checkAllDigit(winningList: List<String>): ErrorType =
		ParseError.INVALID_NUMBER_FORMAT.takeIf { winningList.all { checkDigit(it) == Common.NON_ERROR } }
			?: Common.NON_ERROR

	fun checkWinningSize(numbers: List<Int>): ErrorType =
		WinningError.INVALID_SIZE.takeIf { numbers.size != GamePolicy.LOTTO_SIZE } ?: Common.NON_ERROR

	private fun checkWinningUniqueness(numbers: List<Int>): ErrorType =
		WinningError.DUPLICATE_NUMBER.takeIf { numbers.distinct().size != GamePolicy.LOTTO_SIZE } ?: Common.NON_ERROR

	private fun checkWinningRange(numbers: List<Int>): ErrorType =
		WinningError.NOT_IN_RANGE.takeIf { numbers.any { it !in GamePolicy.VALID_NUMBER_RANGE } } ?: Common.NON_ERROR

	private fun checkBonusRange(bonus: Int): ErrorType =
		BonusError.NOT_IN_RANGE.takeIf { bonus !in GamePolicy.VALID_NUMBER_RANGE } ?: Common.NON_ERROR

	private fun checkBonusNotInWinning(bonus: Int, winning: List<Int>): ErrorType =
		BonusError.DUPLICATE_NUMBER.takeIf { bonus in winning } ?: Common.NON_ERROR

	private fun checkAmountDivisible(amount: Long): ErrorType =
		PurchaseError.NOT_DIVISIBLE_BY_TICKET_PRICE.takeIf { !isDivisibleByTicketPrice(amount) } ?: Common.NON_ERROR

	private fun checkPurchasableAmount(amount: Long): ErrorType =
		PurchaseError.CANNOT_AFFORD_TICKET.takeIf { !isAfford(amount) } ?: Common.NON_ERROR

	private fun isDivisibleByTicketPrice(amount: Long) = amount % GamePolicy.TICKET_PRICE == 0L
	private fun isAfford(amount: Long) = amount >= GamePolicy.TICKET_PRICE

}