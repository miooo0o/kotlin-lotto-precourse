package lotto.policy

/**
 * Defines shared rules and constraints for the Lotto domain.
 *
 * This object provides constant values and validation logic
 * used throughout the application, particularly during input
 * validation and unit testing.
 */
object LottoPolicy {

	const val TICKET_PRICE = 1000
	const val LOTTO_SIZE = 6
	const val LOTTO_MIN_NUMBER = 1
	const val LOTTO_MAX_NUMBER = 45

	const val ERROR_PREFIX = "[ERROR]: "

	const val MIN_MATCH_COUNT_FOR_REWARD = 3
	const val BONUS_NUMBER_COUNT = 1

	val VALID_NUMBER_RANGE = LOTTO_MIN_NUMBER..LOTTO_MAX_NUMBER

	const val LOTTO_NUMBERS_SORTED = true
	const val BONUS_NUMBER_MUST_NOT_DUPLICATE = true
}