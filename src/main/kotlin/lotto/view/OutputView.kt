package lotto.view

import lotto.domain.Lotto
import lotto.domain.LottoEvaluator
import lotto.domain.Rank
import lotto.error.isStatusSuccess
import lotto.policy.GamePolicy
import lotto.policy.hasValidSize
import lotto.policy.isNotInWinningNumbers
import lotto.util.requireOrUnexpected

object OutputView {
	fun viewPurchasedTickets(lottoList: List<Lotto>) {
		lottoList.viewPurchasedTickets()
	}
}

fun List<Lotto>.viewPurchasedTickets() {
	requireOrUnexpected(this.size >= GamePolicy.MIN_TICKET_SIZE) {
		"The registered lotto must be ${GamePolicy.MIN_TICKET_SIZE} or more"
	}

	println("You have purchased ${this.size} tickets.")
	forEach { it.display() }
	println()
}

fun List<Lotto>.evaluateRanks(
	winningNumbers: List<Int>,
	bonusNumber: Int
): Map<Rank, Int> {
	basicRequireForDisplayOrThrow(this, winningNumbers, bonusNumber)
	return LottoEvaluator.evaluate(this, winningNumbers, bonusNumber)
}

fun Map<Rank, Int>.displayMatches() {
	showLottoMap(this)
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
		bonusNumber.isNotInWinningNumbers(winningNumbers).isStatusSuccess()
	) { "Bonus number must not overlap with winning numbers." }
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
	return String.format("%,d", prize)
}