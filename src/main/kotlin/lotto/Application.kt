package lotto

import lotto.error.UnexpectedException
import lotto.policy.GamePolicy
import lotto.view.InputView
import lotto.view.repeatUntilValid

fun main() {

	try {

		// Please enter the purchase amount.
		val amount = repeatUntilValid(GamePolicy.AMOUNT_MESSAGE) { InputView.parseAmountOrThrow(it) }

		// -> You have purchased 8 tickets. (LottoIssuer -> Lotto: List<Int>)

		// Please enter last week's winning numbers.
		val winning = repeatUntilValid(GamePolicy.WINNING_MESSAGE) { InputView.parseWinningNumbersOrThrow(it) }

		// Please enter the bonus number.
		val bonus = repeatUntilValid(GamePolicy.BONUS_MESSAGE) { InputView.parseBonusNumberOrThrow(it) }

		// Eval Lotto -> Print Winning Statistics

	} catch (e: UnexpectedException) {
		// Programme exit (1)
	}
}
