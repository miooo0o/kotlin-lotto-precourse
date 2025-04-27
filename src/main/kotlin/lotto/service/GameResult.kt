package lotto.service

import lotto.domain.Lotto
import lotto.domain.LottoEvaluator
import lotto.domain.Rank

data class GameResult(
	val lottoList: List<Lotto>,
	val winningNumbers: List<Int>,
	val bonusNumber: Int,
	val amount: Long
) {
	fun evaluateRanks(): Map<Rank, Int> {
		return LottoEvaluator.evaluate(lottoList, winningNumbers, bonusNumber)
	}

	fun calculateProfitRate(): Double {
		val totalPrize = evaluateRanks()
			.map { (rank, count) -> rank.prize.toLong() * count }
			.sum()
		return (totalPrize.toDouble() / amount) * 100
	}
}