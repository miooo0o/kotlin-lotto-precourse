package lotto.validator

import lotto.error.Bonus
import lotto.error.ErrorType
import lotto.error.Purchase
import lotto.error.Winning
import lotto.policy.GamePolicy

object Validator {
	fun isValidWinningNumbers(winning: List<Int>): ErrorType? =
		checkWinningSize(winning)
			?: checkWinningUniqueness(winning)
			?: checkWinningRange(winning)

	fun isValidBonusNumber(bonus: Int, winning: List<Int>): ErrorType? =
		checkBonusRange(bonus)
			?: checkBonusNotInWinning(bonus, winning)

	fun isValidAmount(amount: Long): ErrorType? =
		checkPurchasable(amount)
			?: checkAmountDivisible(amount)

	fun checkWinningSize(numbers: List<Int>): ErrorType? =
		Winning.INVALID_SIZE.takeIf { numbers.size != GamePolicy.LOTTO_SIZE }

	private fun checkWinningUniqueness(numbers: List<Int>): ErrorType? =
		Winning.DUPLICATE_NUMBER.takeIf { numbers.distinct().size != GamePolicy.LOTTO_SIZE }

	private fun checkWinningRange(numbers: List<Int>): ErrorType? =
		Winning.NOT_IN_RANGE.takeIf { numbers.any { it !in GamePolicy.VALID_NUMBER_RANGE } }

	private fun checkBonusRange(bonus: Int): ErrorType? =
		Bonus.NOT_IN_RANGE.takeIf { bonus !in GamePolicy.VALID_NUMBER_RANGE }

	private fun checkBonusNotInWinning(bonus: Int, winning: List<Int>): ErrorType? =
		Bonus.DUPLICATE_NUMBER.takeIf { bonus in winning }

	private fun checkAmountDivisible(amount: Long): ErrorType? =
		Purchase.NOT_DIVISIBLE_BY_TICKET_PRICE.takeIf { !isDivisibleByTicketPrice(amount) }

	private fun checkPurchasable(amount: Long): ErrorType? =
		Purchase.INVALID_PURCHASE_AMOUNT.takeIf { !isPurchasable(amount) }

	private fun isDivisibleByTicketPrice(amount: Long) = amount % GamePolicy.TICKET_PRICE == 0L
	private fun isPurchasable(amount: Long) = amount >= GamePolicy.TICKET_PRICE

}