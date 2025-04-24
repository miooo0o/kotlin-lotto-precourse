package lotto.validator

import lotto.error.*
import lotto.policy.GamePolicy

object Validator {
	fun isValidWinningNumbers(winning: List<Int>): ErrorType {
		val sizeError = checkWinningSize(winning)
		if (sizeError.isStatusFailure()) return sizeError

		val duplicateError = checkWinningUniqueness(winning)
		if (duplicateError.isStatusFailure()) return duplicateError

		val rangeError = checkWinningRange(winning)
		if (rangeError.isStatusFailure()) return rangeError

		return Common.NON_ERROR
	}

	fun isValidBonusNumber(bonus: Int, winning: List<Int>): ErrorType {
		val rangeError = checkBonusRange(bonus)
		if (rangeError.isStatusFailure()) return rangeError

		val duplicateError = checkBonusNotInWinning(bonus, winning)
		if (duplicateError.isStatusFailure()) return duplicateError

		return Common.NON_ERROR
	}

	fun isValidAmount(amount: Long): ErrorType {
		val purchasableError = checkPurchasableAmount(amount)
		if (purchasableError.isStatusFailure()) return purchasableError
		return checkAmountDivisible(amount)
	}

	fun checkWinningSize(numbers: List<Int>): ErrorType =
		Winning.INVALID_SIZE.takeIf { numbers.size != GamePolicy.LOTTO_SIZE } ?: Common.NON_ERROR

	private fun checkWinningUniqueness(numbers: List<Int>): ErrorType =
		Winning.DUPLICATE_NUMBER.takeIf { numbers.distinct().size != GamePolicy.LOTTO_SIZE } ?: Common.NON_ERROR

	private fun checkWinningRange(numbers: List<Int>): ErrorType =
		Winning.NOT_IN_RANGE.takeIf { numbers.any { it !in GamePolicy.VALID_NUMBER_RANGE } } ?: Common.NON_ERROR

	private fun checkBonusRange(bonus: Int): ErrorType =
		Bonus.NOT_IN_RANGE.takeIf { bonus !in GamePolicy.VALID_NUMBER_RANGE } ?: Common.NON_ERROR

	private fun checkBonusNotInWinning(bonus: Int, winning: List<Int>): ErrorType =
		Bonus.DUPLICATE_NUMBER.takeIf { bonus in winning } ?: Common.NON_ERROR

	private fun checkAmountDivisible(amount: Long): ErrorType =
		Purchase.NOT_DIVISIBLE_BY_TICKET_PRICE.takeIf { !isDivisibleByTicketPrice(amount) } ?: Common.NON_ERROR

	private fun checkPurchasableAmount(amount: Long): ErrorType =
		Purchase.INVALID_PURCHASE_AMOUNT.takeIf { !isPurchasable(amount) } ?: Common.NON_ERROR

	private fun isDivisibleByTicketPrice(amount: Long) = amount % GamePolicy.TICKET_PRICE == 0L
	private fun isPurchasable(amount: Long) = amount >= GamePolicy.TICKET_PRICE

}