package lotto.error

sealed interface ErrorType

enum class Common : ErrorType {
	NON_ERROR
}

enum class ParseError : ErrorType {
	INVALID_NUMBER_FORMAT,
	INVALID_RANGE
}

enum class WinningError : ErrorType {
	INVALID_SIZE,
	NOT_IN_RANGE,
	DUPLICATE_NUMBER
}

enum class BonusError : ErrorType {
	NOT_IN_RANGE,
	DUPLICATE_NUMBER
}

enum class PurchaseError : ErrorType {
	CANNOT_AFFORD_TICKET,
	NOT_DIVISIBLE_BY_TICKET_PRICE
}

enum class LogicError : ErrorType {
	INVALID_LOGIC,
	CONVERSION_FAILED,
	DEFAULT
}