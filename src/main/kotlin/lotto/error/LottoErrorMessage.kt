package lotto.error

import lotto.policy.LottoPolicy

object LottoErrorMessage {

	fun of(type: LottoErrorType): String = when (type) {
		Winning.INVALID_SIZE ->
			ErrorTemplate.numberCount(LottoPolicy.LOTTO_SIZE)

		Winning.NOT_IN_RANGE ->
			ErrorTemplate.numberRange("lotto numbers", LottoPolicy.LOTTO_MIN_NUMBER, LottoPolicy.LOTTO_MAX_NUMBER)

		Winning.DUPLICATE_NUMBER ->
			ErrorTemplate.duplicate("numbers")

		Bonus.NOT_IN_RANGE ->
			ErrorTemplate.numberRange("bonus number", LottoPolicy.LOTTO_MIN_NUMBER, LottoPolicy.LOTTO_MAX_NUMBER)

		Bonus.DUPLICATE_NUMBER ->
			ErrorTemplate.duplicate("bonus number")

		Purchase.INVALID_PURCHASE_AMOUNT ->
			ErrorTemplate.invalidPurchaseAmount(LottoPolicy.TICKET_PRICE)

		Purchase.NOT_DIVISIBLE_BY_TICKET_PRICE ->
			ErrorTemplate.notDivisibleBy("purchase amount", LottoPolicy.TICKET_PRICE)
	}
}