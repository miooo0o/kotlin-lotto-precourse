package lotto.policy

/**
 * Defines shared rules and constraints for the Lotto domain.
 *
 * This object provides constant values and validation logic
 * used throughout the application, particularly during input
 * validation and unit testing.
 */
object GamePolicy {

	// Ticket Rules
	const val TICKET_PRICE = 1000L

	// Lotto Rules
	const val LOTTO_SIZE = 6

	const val LOTTO_MIN_NUMBER = 1
	const val LOTTO_MAX_NUMBER = 45

	val VALID_NUMBER_RANGE = LOTTO_MIN_NUMBER..LOTTO_MAX_NUMBER

	const val LOTTO_NUMBERS_SORTED = true
	const val MIN_MATCH_COUNT_FOR_REWARD = 3

	// Lotto Bonus Rules
	const val BONUS_NUMBER_COUNT = 1
	const val BONUS_NUMBER_MUST_NOT_DUPLICATE = true

	// Error Rules
	const val ERROR_PREFIX = "[ERROR]:"

	// Message
	const val WINNING_MESSAGE = "Please enter last week's winning numbers."
	const val BONUS_MESSAGE = "Please enter the bonus number."
	const val AMOUNT_MESSAGE = "Please enter the purchase amount."
}