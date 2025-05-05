package lotto

object OutputView {
	fun displayListOfLotto(listOfLotto: List<Lotto>) {
		println("You have purchased ${listOfLotto.size} tickets.")
		println(listOfLotto.joinToString("\n"))
	}

	fun displayResults(results: Map<Rank, Int>, amount: Long) {

		println("Winning Statistics")
		println("---")
		for ((rank, ticketCount) in results) {
			if (rank == Rank.NONE) continue
			val bonus = if (rank.hasBonus) " + Bonus Ball" else ""
			println("${rank.matchCount} Matches${bonus} (${"%,d".format(rank.prize)} KRW) – $ticketCount tickets")
		}
		val totalPrize = results.entries.sumOf { (rank, count) -> rank.prize * count }
		val returnRate = String.format("%.1f", totalPrize.toDouble() / amount.toDouble() * 100)
		println("Total return rate is $returnRate%.")

	}
}