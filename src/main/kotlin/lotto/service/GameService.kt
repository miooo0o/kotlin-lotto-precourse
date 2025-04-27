package lotto.service

import lotto.Lotto

import lotto.domain.LottoIssuer
import lotto.policy.GamePolicy
import lotto.policy.doesNotOverlapWithWinningNumbers
import lotto.view.InputView
import lotto.view.OutputView
import lotto.view.repeatUntilValid

object GameService {
	fun start(): GameResult {
		val (lottoList, amount) = purchaseLottos()
		val (winningNumbers, bonusNumber) = readWinningInfo()
		return GameResult(lottoList, winningNumbers, bonusNumber, amount)
	}

	fun show(result: GameResult) {
		val matchResults = result.evaluateRanks()
		val profitRate = result.calculateProfitRate()

		OutputView.displayMatches(matchResults)
		OutputView.displayProfitRate(profitRate)
	}

	private fun purchaseLottos(): Pair<List<Lotto>, Long> {
		val amount = readPurchaseAmount()
		val lottoList = issueLottoList(amount)
		OutputView.displayPurchasedTickets(lottoList)
		return lottoList to amount
	}

	private fun readWinningInfo(): Pair<List<Int>, Int> {
		val winningNumbers = readWinningNumbers()
		val bonusNumber = readBonusNumber(winningNumbers)
		return winningNumbers to bonusNumber
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
			validator = { it.doesNotOverlapWithWinningNumbers(winning) }
		)
		println()
		return bonus
	}
}