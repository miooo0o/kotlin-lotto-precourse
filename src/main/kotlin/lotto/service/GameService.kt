package lotto.service

import lotto.domain.Lotto
import lotto.domain.LottoIssuer
import lotto.policy.GamePolicy
import lotto.policy.isNotInWinningNumbers
import lotto.view.*

object GameService {
	fun start() {
		val amount = readPurchaseAmount()

		val lottoList = issueLottoList(amount)
		OutputView.viewPurchasedTickets(lottoList)

		val winningNumbers = readWinningNumbers()
		val bonusNumber = readBonusNumber(winningNumbers)

		val map = lottoList.evaluateRanks(winningNumbers, bonusNumber)
		map.displayMatches()

		// -> Total return is (double.0)%.
	}

	private fun readPurchaseAmount(): Long {
		return repeatUntilValid(
			prompt = GamePolicy.AMOUNT_MESSAGE,
			reader = { InputView.parseAmountOrThrow(it) }
		)
	}

	private fun issueLottoList(amount: Long): List<Lotto> {
		val issuer = LottoIssuer(amount)
		return issuer.issue()
	}

	private fun viewPurchasedTickets(lottos: List<Lotto>) {
		lottos.viewPurchasedTickets()
	}

	private fun readWinningNumbers(): List<Int> {
		val winning = repeatUntilValid(
			prompt = GamePolicy.WINNING_MESSAGE,
			reader = { InputView.parseWinningNumbersOrThrow(it) }
		)
		println()
		return winning.sorted()
	}

	private fun readBonusNumber(winning: List<Int>): Int {
		val bonus = repeatUntilValid(
			prompt = GamePolicy.BONUS_MESSAGE,
			reader = { InputView.parseBonusNumberOrThrow(it) },
			validator = { it.isNotInWinningNumbers(winning) }
		)
		println()
		return bonus
	}
}