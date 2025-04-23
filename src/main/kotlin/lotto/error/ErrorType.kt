package lotto.error

sealed interface LottoErrorType

enum class Winning : LottoErrorType {
	INVALID_SIZE,
	NOT_IN_RANGE,
	DUPLICATE_NUMBER
}

enum class Bonus : LottoErrorType {
	NOT_IN_RANGE,
	DUPLICATE_NUMBER
}

enum class Purchase : LottoErrorType {
	INVALID_PURCHASE_AMOUNT,
	NOT_DIVISIBLE_BY_TICKET_PRICE
}