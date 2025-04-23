package lotto.policy

object Validator {

	fun isValidLottoNumbers(numbers: List<Int>) {
		require(isCorrectSize(numbers)) { "[ERROR]: Lotto numbers must contain exactly ${LottoPolicy.LOTTO_SIZE} numbers. (given: ${numbers.size})" }
		require(hasNoDuplicates(numbers)) { "[ERROR]: Duplicate numbers are not allowed." }
		require(isInValidRange(numbers)) { "[ERROR]: All numbers must be between ${LottoPolicy.LOTTO_MIN_NUMBER} and ${LottoPolicy.LOTTO_MAX_NUMBER}." }
	}

	fun isValidBonusNumber(bonus: Int, winning: List<Int>) {
		require(isInValidRange(bonus)) { "[ERROR]: Bonus number must be between ${LottoPolicy.LOTTO_MIN_NUMBER} and ${LottoPolicy.LOTTO_MAX_NUMBER}. (given: $bonus)" }
		require(
			isBonusNotDuplicated(
				bonus,
				winning
			)
		) { "[ERROR]: Bonus number must not be one of the winning numbers. (given: $bonus)" }
	}

	fun isValidPurchaseAmount(amount: Long): Boolean =
		LottoPolicy.isPurchasable(amount)

	private fun isCorrectSize(numbers: List<Int>): Boolean =
		numbers.size == LottoPolicy.LOTTO_SIZE

	private fun hasNoDuplicates(numbers: List<Int>): Boolean =
		numbers.distinct().size == LottoPolicy.LOTTO_SIZE

	private fun isInValidRange(numbers: List<Int>): Boolean =
		numbers.all { it in LottoPolicy.VALID_NUMBER_RANGE }

	private fun isInValidRange(bonus: Int): Boolean =
		bonus in LottoPolicy.VALID_NUMBER_RANGE

	private fun isBonusNotDuplicated(bonus: Int, winning: List<Int>): Boolean =
		bonus !in winning
}