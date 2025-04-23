package lotto.error

sealed interface ErrorType

enum class Winning : ErrorType {
	INVALID_SIZE,
	NOT_IN_RANGE,
	DUPLICATE_NUMBER
}

enum class Bonus : ErrorType {
	NOT_IN_RANGE,
	DUPLICATE_NUMBER
}

enum class Purchase : ErrorType {
	INVALID_PURCHASE_AMOUNT,
	NOT_DIVISIBLE_BY_TICKET_PRICE
}