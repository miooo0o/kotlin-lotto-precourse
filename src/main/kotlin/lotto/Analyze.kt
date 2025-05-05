package lotto

object Analyze {

	fun result(
		listOfLotto: List<Lotto>,
		winningNumbers: List<Int>,
		bonusNumber: Int,
	): Map<Rank, Int> {
		val result: MutableMap<Rank, Int> = Rank.entries.associateWith { 0 }.toMutableMap()
		for (lotto in listOfLotto) {
			val match = lotto.countMatch(winningNumbers)
			val hasBonus = lotto.hasBonus(bonusNumber)
			val rank = Rank.from(match, hasBonus)
			result[rank] = result[rank]!! + 1
		}
		return result
	}

}