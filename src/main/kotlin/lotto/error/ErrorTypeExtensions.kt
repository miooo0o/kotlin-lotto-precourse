package lotto.error

import lotto.policy.GamePolicy

fun ErrorType.toMessage(): String {
	return when (this) {
		is WinningError -> fromWinning(this)
		is BonusError -> fromBonus(this)
		is PurchaseError -> fromPurchase(this)
		is LogicError -> fromLogic(this)
		is ParseError -> fromParse(this)
		Common.NON_ERROR -> "no error"
	}
}

fun ErrorType.isTypeRetry(): Boolean =
	this is ParseError || this is WinningError || this is BonusError || this is PurchaseError

fun ErrorType.isTypeLogic(): Boolean =
	this is LogicError

fun ErrorType.isStatusFailure(): Boolean = this != Common.NON_ERROR

private fun fromWinning(type: WinningError): String = when (type) {
	WinningError.INVALID_SIZE -> ErrorTemplate.numberCount(GamePolicy.LOTTO_SIZE)
	WinningError.NOT_IN_RANGE -> ErrorTemplate.numberRange(
		"lotto numbers",
		GamePolicy.LOTTO_MIN_NUMBER,
		GamePolicy.LOTTO_MAX_NUMBER
	)

	WinningError.DUPLICATE_NUMBER -> ErrorTemplate.duplicate("numbers")
}

private fun fromBonus(type: BonusError): String = when (type) {
	BonusError.NOT_IN_RANGE -> ErrorTemplate.numberRange(
		"bonus number",
		GamePolicy.LOTTO_MIN_NUMBER,
		GamePolicy.LOTTO_MAX_NUMBER
	)

	BonusError.DUPLICATE_NUMBER -> ErrorTemplate.duplicate("bonus number")
}

private fun fromPurchase(type: PurchaseError): String = when (type) {
	PurchaseError.CANNOT_AFFORD_TICKET -> ErrorTemplate.invalidPurchaseAmount(GamePolicy.TICKET_PRICE)
	PurchaseError.NOT_DIVISIBLE_BY_TICKET_PRICE -> ErrorTemplate.notDivisibleBy(
		"purchase amount",
		GamePolicy.TICKET_PRICE
	)
}

// FIXME: better message
private fun fromLogic(type: LogicError): String = when (type) {
	LogicError.DEFAULT -> "DEFAULT"
	LogicError.INVALID_LOGIC -> "INVALID_LOGIC"
	LogicError.CONVERSION_FAILED -> "CONVERSION_FAILED"
}

// FIXME: better message
private fun fromParse(type: ParseError): String = when (type) {
	ParseError.NULL_FOUND -> "EMPTY_INPUT"
	ParseError.INVALID_FORMAT -> "INVALID_NUMBER_FORMAT"
	ParseError.INVALID_RANGE -> "INVALID_RANGE"
}