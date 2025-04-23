package lotto.error

import lotto.policy.LottoPolicy

class LottoError {

	enum class LottoError(val message: String) {
		INVALID_SIZE("Lotto numbers must contain exactly ${LottoPolicy.LOTTO_SIZE} numbers."),
		DUPLICATE_NUMBER("Duplicate numbers are not allowed."),
		NOT_IN_RANGE(rangeMessage("All numbers")),
		BONUS_NOT_IN_RANGE(rangeMessage("Bonus number")),
		DUPLICATE_BONUS_NUMBER("Bonus number must not be one of the winning numbers.");
	}

	companion object {
		private fun rangeMessage(label: String): String =
			"$label must be between ${LottoPolicy.LOTTO_MIN_NUMBER} and ${LottoPolicy.LOTTO_MAX_NUMBER}."
	}
}