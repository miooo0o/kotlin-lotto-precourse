package lotto.error

import lotto.policy.GamePolicy

object ErrorFormatter {
	fun of(type: LottoErrorType): String = when (type) {
		is Winning -> fromWinning(type)
		is Bonus -> fromBonus(type)
		is Purchase -> fromPurchase(type)
	}

	private fun fromWinning(type: Winning): String = when (type) {
		Winning.INVALID_SIZE -> ErrorTemplate.numberCount(GamePolicy.LOTTO_SIZE)
		Winning.NOT_IN_RANGE -> ErrorTemplate.numberRange(
			"lotto numbers",
			GamePolicy.LOTTO_MIN_NUMBER,
			GamePolicy.LOTTO_MAX_NUMBER
		)

		Winning.DUPLICATE_NUMBER -> ErrorTemplate.duplicate("numbers")
	}

	private fun fromBonus(type: Bonus): String = when (type) {
		Bonus.NOT_IN_RANGE -> ErrorTemplate.numberRange(
			"bonus number",
			GamePolicy.LOTTO_MIN_NUMBER,
			GamePolicy.LOTTO_MAX_NUMBER
		)

		Bonus.DUPLICATE_NUMBER -> ErrorTemplate.duplicate("bonus number")
	}

	private fun fromPurchase(type: Purchase): String = when (type) {
		Purchase.INVALID_PURCHASE_AMOUNT -> ErrorTemplate.invalidPurchaseAmount(GamePolicy.TICKET_PRICE)
		Purchase.NOT_DIVISIBLE_BY_TICKET_PRICE -> ErrorTemplate.notDivisibleBy(
			"purchase amount",
			GamePolicy.TICKET_PRICE
		)
	}
}