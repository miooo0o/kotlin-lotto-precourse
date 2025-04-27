package lotto.service

import lotto.Lotto
import lotto.domain.LottoEvaluator
import lotto.domain.Rank
import lotto.error.isStatusSuccess
import lotto.policy.doesNotOverlapWithWinningNumbers
import lotto.util.requireOrUnexpected

data class GameResult(
	val lottoList: List<Lotto>,
	val winningNumbers: List<Int>,
	val bonusNumber: Int,
	val amount: Long,
) {
	init {
		validateInputs(lottoList, winningNumbers, bonusNumber)
	}

	fun evaluateRanks(): Map<Rank, Int> {
		return LottoEvaluator.evaluate(lottoList, winningNumbers, bonusNumber)
	}

	fun calculateProfitRate(): Double {
		val matchResults = evaluateRanks()
		val totalPrize = matchResults
			.map { (rank, count) -> rank.prize.toLong() * count }
			.sum()

		return (totalPrize.toDouble() / amount) * 100
	}

	private fun validateInputs(
		lottoList: List<Lotto>,
		winningNumbers: List<Int>,
		bonusNumber: Int,
	) {
		requireOrUnexpected(lottoList.isNotEmpty()) {
			"Lotto list must not be empty."
		}
		requireOrUnexpected(winningNumbers.isNotEmpty()) {
			"Winning numbers must not be empty."
		}
		requireOrUnexpected(bonusNumber.doesNotOverlapWithWinningNumbers(winningNumbers).isStatusSuccess()) {
			"Bonus number must not overlap with winning numbers."
		}
	}
}