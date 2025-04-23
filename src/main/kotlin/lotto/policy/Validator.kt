package lotto.policy

import lotto.error.Bonus
import lotto.error.LottoErrorType
import lotto.error.Purchase
import lotto.error.Winning
import lotto.policy.LottoPolicy.TICKET_PRICE

object Validator {
	fun isValidWinningNumbers(winning: List<Int>): LottoErrorType? =
		checkWinningSize(winning)
			?: checkWinningUniqueness(winning)
			?: checkWinningRange(winning)

	fun isValidBonusNumber(bonus: Int, winning: List<Int>): LottoErrorType? =
		checkBonusRange(bonus)
			?: checkBonusNotInWinning(bonus, winning)

	fun isValidAmount(amount: Long): LottoErrorType? =
		checkPurchasable(amount)
			?: checkAmountDivisible(amount)

	fun checkWinningSize(numbers: List<Int>): LottoErrorType? =
		Winning.INVALID_SIZE.takeIf { numbers.size != LottoPolicy.LOTTO_SIZE }

	private fun checkWinningUniqueness(numbers: List<Int>): LottoErrorType? =
		Winning.DUPLICATE_NUMBER.takeIf { numbers.distinct().size != LottoPolicy.LOTTO_SIZE }

	private fun checkWinningRange(numbers: List<Int>): LottoErrorType? =
		Winning.NOT_IN_RANGE.takeIf { numbers.any { it !in LottoPolicy.VALID_NUMBER_RANGE } }

	private fun checkBonusRange(bonus: Int): LottoErrorType? =
		Bonus.NOT_IN_RANGE.takeIf { bonus !in LottoPolicy.VALID_NUMBER_RANGE }

	private fun checkBonusNotInWinning(bonus: Int, winning: List<Int>): LottoErrorType? =
		Bonus.DUPLICATE_NUMBER.takeIf { bonus in winning }

	private fun checkAmountDivisible(amount: Long): LottoErrorType? =
		Purchase.NOT_DIVISIBLE_BY_TICKET_PRICE.takeIf { !isDivisibleByTicketPrice(amount) }

	private fun checkPurchasable(amount: Long): LottoErrorType? =
		Purchase.INVALID_PURCHASE_AMOUNT.takeIf { !isPurchasable(amount) }

	private fun isDivisibleByTicketPrice(amount: Long) = amount % TICKET_PRICE == 0L
	private fun isPurchasable(amount: Long) = amount >= TICKET_PRICE

}