package lotto

class LottoMachine(
	private val generator: Generator = GenerateLottoNumbers
) {
	fun asMuch(amount: Long): List<Lotto> {
		val ticketCount = (amount / 1000).toInt()
		return purchase(ticketCount)
	}

	private fun purchase(ticketCount: Int): List<Lotto> {
		return List(ticketCount) {
			val randomNumbers = generator.generate()
			require(randomNumbers.size == 6) { "lotto numbers size should be 6" }
			require(randomNumbers.toSet().size == 6) { "duplicate number found" }
			require(randomNumbers.all { it in 1..45 }) { "not in the range" }
			Lotto(randomNumbers.sorted())
		}
	}
}