package lotto.service

import lotto.domain.Lotto
import lotto.domain.LottoEvaluator
import lotto.domain.LottoIssuer
import lotto.domain.Rank
import lotto.error.isStatusSuccess
import lotto.policy.GamePolicy
import lotto.policy.doesNotOverlapWithWinningNumbers
import lotto.policy.hasValidSize
import lotto.util.requireOrUnexpected
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
		val matchResults = evaluateRanks(
			result.lottoList, result.winningNumbers, result.bonusNumber
		)
		OutputView.displayMatches(matchResults)
		OutputView.displayProfitRate(matchResults, result.amount)
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

	// TODO: move to GameResult
	private fun evaluateRanks(
		lottoList: List<Lotto>,
		winningNumbers: List<Int>,
		bonusNumber: Int
	): Map<Rank, Int> {
		basicRequireForDisplayOrThrow(lottoList, winningNumbers, bonusNumber)
		return LottoEvaluator.evaluate(lottoList, winningNumbers, bonusNumber)
	}

	private fun basicRequireForDisplayOrThrow(
		lottoList: List<Lotto>,
		winningNumbers: List<Int>,
		bonusNumber: Int
	) {
		requireOrUnexpected(lottoList.isNotEmpty()) { "No lotto list to evaluate." }
		requireOrUnexpected(
			winningNumbers.hasValidSize().isStatusSuccess()
		) { "Winning numbers must have exactly ${GamePolicy.LOTTO_SIZE} numbers." }
		requireOrUnexpected(
			bonusNumber.doesNotOverlapWithWinningNumbers(winningNumbers).isStatusSuccess()
		) { "Bonus number must not overlap with winning numbers." }
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