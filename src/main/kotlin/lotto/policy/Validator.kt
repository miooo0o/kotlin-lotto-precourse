package lotto.policy

object Validator {

	fun isValidLottoNumbers(numbers: List<Int>) {
		require(isCorrectSize(numbers)) { "[ERROR]: Lotto numbers must contain exactly ${LottoPolicy.LOTTO_SIZE} numbers. (given: ${numbers.size})" }
		require(hasNoDuplicates(numbers)) { "[ERROR]: Duplicate numbers are not allowed." }
		require(isInValidRange(numbers)) { "[ERROR]: All numbers must be between ${LottoPolicy.LOTTO_MIN_NUMBER} and ${LottoPolicy.LOTTO_MAX_NUMBER}." }
	}

	fun isValidBonusNumber(bonus: Int, winning: List<Int>): Boolean =
		bonus in LottoPolicy.VALID_NUMBER_RANGE && bonus !in winning

	fun isValidPurchaseAmount(amount: Long): Boolean =
		amount / LottoPolicy.TICKET_PRICE > 0 && amount % LottoPolicy.TICKET_PRICE == 0L

	private fun isCorrectSize(numbers: List<Int>): Boolean =
		numbers.size == LottoPolicy.LOTTO_SIZE

	private fun hasNoDuplicates(numbers: List<Int>): Boolean =
		numbers.distinct().size == LottoPolicy.LOTTO_SIZE

	private fun isInValidRange(numbers: List<Int>): Boolean =
		numbers.all { it in LottoPolicy.VALID_NUMBER_RANGE }
}