package lotto

import lotto.domain.LottoIssuer
import lotto.error.UnexpectedException
import lotto.policy.GamePolicy
import lotto.policy.isNotInWinningNumbers
import lotto.view.InputView
import lotto.view.repeatUntilValid
import lotto.view.viewPurchasedTickets

fun main() {

	try {

		// -> Please enter the purchase amount.
		val amount = repeatUntilValid<Long>(
			prompt = GamePolicy.AMOUNT_MESSAGE,
			reader = { InputView.parseAmountOrThrow(it) }
		)

		// Create Lotto-s: LottoIssuer -> Lotto: List<Int>
		val issuer = LottoIssuer(amount)
		val lottos = issuer.issue()

		// -> You have purchased 8 tickets.
		lottos.viewPurchasedTickets()

		// -> Please enter last week's winning numbers.
		val winning = repeatUntilValid(
			prompt = GamePolicy.WINNING_MESSAGE,
			reader = { InputView.parseWinningNumbersOrThrow(it) }
		)
		println() // newline

		// -> Please enter the bonus number.
		val bonus = repeatUntilValid(
			prompt = GamePolicy.BONUS_MESSAGE,
			reader = { InputView.parseBonusNumberOrThrow(it) },
			validator = { it.isNotInWinningNumbers(winning) }
		)
		println() // newline

		// Eval Lotto
		// -> Winning Statistics + "\n" + "---"
		// -> Matches (...)
		// -> Total return is (double.0)%.

	} catch (e: UnexpectedException) {
		throw e
	}
}
