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
		if (numbers.size != LottoPolicy.LOTTO_SIZE) Winning.INVALID_SIZE else null

	private fun checkWinningUniqueness(numbers: List<Int>): LottoErrorType? =
		if (numbers.distinct().size != LottoPolicy.LOTTO_SIZE) Winning.DUPLICATE_NUMBER else null

	private fun checkWinningRange(numbers: List<Int>): LottoErrorType? =
		if (numbers.any { it !in LottoPolicy.VALID_NUMBER_RANGE }) Winning.NOT_IN_RANGE else null

	private fun checkBonusRange(bonus: Int): LottoErrorType? =
		if (bonus !in LottoPolicy.VALID_NUMBER_RANGE) Bonus.NOT_IN_RANGE else null

	private fun checkBonusNotInWinning(bonus: Int, winning: List<Int>): LottoErrorType? =
		if (bonus in winning) Bonus.DUPLICATE_NUMBER else null

	private fun checkAmountDivisible(amount: Long): LottoErrorType? =
		if (!isDivisibleByTicketPrice(amount)) Purchase.NOT_DIVISIBLE_BY_TICKET_PRICE else null

	private fun checkPurchasable(amount: Long): LottoErrorType? =
		if (!isPurchasable(amount)) Purchase.INVALID_PURCHASE_AMOUNT else null

	private fun isDivisibleByTicketPrice(amount: Long) = amount % TICKET_PRICE == 0L
	private fun isPurchasable(amount: Long) = amount >= TICKET_PRICE

}