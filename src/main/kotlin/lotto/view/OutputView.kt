package lotto.view

import lotto.domain.Lotto
import lotto.domain.Rank
import lotto.policy.GamePolicy
import lotto.util.requireOrUnexpected

object OutputView {

	fun displayPurchasedTickets(lottoList: List<Lotto>) {
		requireOrUnexpected(lottoList.size >= GamePolicy.MIN_TICKET_SIZE) {
			"The registered lotto must be ${GamePolicy.MIN_TICKET_SIZE} or more"
		}

		println("You have purchased ${lottoList.size} tickets.")
		lottoList.forEach { it.display() }
		println()
	}

	fun displayMatches(matchResults: Map<Rank, Int>) {
		showLottoMap(matchResults)
	}

	// TODO: move to GameResult
	fun displayProfitRate(
		rankCountMap: Map<Rank, Int>,
		totalPurchaseAmount: Long
	) {
		val totalPrize = rankCountMap.entries.sumOf { (rank, count) ->
			rank.prize.toLong() * count
		}
		val profitRate = (totalPrize.toDouble() / totalPurchaseAmount) * 100
		val rate = "%.1f".format(profitRate).toDouble()
		println("Total return rate is ${rate}%.")
	}


	private fun showLottoMap(lottoMap: Map<Rank, Int>) {
		println("Winning Statistics")
		println("---")
		listOf(Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST)
			.forEach { rank ->
				println(formatMatchResult(rank, lottoMap))
			}
	}

	private fun formatMatchResult(rank: Rank, lottoMap: Map<Rank, Int>): String {
		return "${rank.matchCount} Matches${if (rank == Rank.SECOND) " + Bonus Ball" else ""}" +
				" (${formatRankPrize(rank.prize)} ${GamePolicy.GAME_CURRENCY}) – ${lottoMap[rank] ?: 0} tickets"
	}

	private fun formatRankPrize(prize: Int): String {
		return "%,d".format(prize)
	}
}