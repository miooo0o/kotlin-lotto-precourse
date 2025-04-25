package lotto.policy

import lotto.error.*

// Long Type
fun Long?.isValidLongValue(): ErrorType =
	ParseError.INVALID_RANGE.takeIf { this == null } ?: Common.NON_ERROR

fun Long.isAtLeastTicketPrice(): ErrorType =
	PurchaseError.CANNOT_AFFORD_TICKET.takeIf { this < GamePolicy.TICKET_PRICE } ?: Common.NON_ERROR

fun Long.isDivisibleByTicketPrice(): ErrorType =
	PurchaseError.NOT_DIVISIBLE_BY_TICKET_PRICE.takeIf { this % GamePolicy.TICKET_PRICE != 0L } ?: Common.NON_ERROR

// String type
fun String.containsOnlyDigits(): ErrorType =
	ParseError.INVALID_NUMBER_FORMAT.takeIf { this.any { !it.isDigit() } } ?: Common.NON_ERROR

// List<String>
fun List<String>.containsOnlyNumeric(): ErrorType =
	ParseError.INVALID_NUMBER_FORMAT.takeIf { this.any { it.containsOnlyDigits().isStatusFailure() } }
		?: Common.NON_ERROR

// List<Int> type
fun List<Int>.hasValidSize(): ErrorType =
	WinningError.INVALID_SIZE.takeIf { this.size != GamePolicy.LOTTO_SIZE } ?: Common.NON_ERROR

fun List<Int>.hasNoDuplicates(): ErrorType =
	WinningError.DUPLICATE_NUMBER.takeIf { this.distinct().size != GamePolicy.LOTTO_SIZE } ?: Common.NON_ERROR

fun List<Int>.isInValidRange(): ErrorType =
	WinningError.NOT_IN_RANGE.takeIf { this.any { it !in GamePolicy.VALID_NUMBER_RANGE } } ?: Common.NON_ERROR

// Int type
fun Int.isInValidRange(): ErrorType =
	BonusError.NOT_IN_RANGE.takeIf { this !in GamePolicy.VALID_NUMBER_RANGE } ?: Common.NON_ERROR

fun Int.isNotInWinningNumbers(winning: List<Int>): ErrorType =
	BonusError.DUPLICATE_NUMBER.takeIf { this in winning } ?: Common.NON_ERROR
