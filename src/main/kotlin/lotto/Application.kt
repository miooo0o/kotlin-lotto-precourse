package lotto

import lotto.error.UnexpectedException
import lotto.policy.GamePolicy
import lotto.view.InputView
import lotto.view.util.repeatUntilValid

fun main() {

	try {
		
		val winning = repeatUntilValid(GamePolicy.WINNING_MESSAGE) { InputView.parseWinningNumbersOrThrow(it) }
		val bonus = repeatUntilValid(GamePolicy.BONUS_MESSAGE) { InputView.parseBonusNumberOrThrow(it) }
		val amount = repeatUntilValid(GamePolicy.AMOUNT_MESSAGE) { InputView.parseAmountOrThrow(it) }

	} catch (e: UnexpectedException) {

	}
}
