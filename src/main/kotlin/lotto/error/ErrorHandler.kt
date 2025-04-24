package lotto.error

class RetryInputException(message: String) : IllegalArgumentException(message) {
}

class UnexpectedException(message: String) : IllegalStateException(message) {
}

object ErrorHandler {
	fun throwIf(errorType: ErrorType) {
		if (errorType == Common.NON_ERROR) return
		if (errorType.isTypeRetry()) throw RetryInputException(ErrorFormatter.of(errorType))
		if (errorType.isTypeLogic()) throw UnexpectedException("error handler -> ${errorType::class.simpleName}") // TODO: better message
	}
}

fun ErrorType.isTypeRetry(): Boolean =
	this is Winning || this is Bonus || this is Purchase

fun ErrorType.isTypeLogic(): Boolean =
	this is Logic

fun ErrorType.isStatusSuccess(): Boolean = this == Common.NON_ERROR

fun ErrorType.isStatusFailure(): Boolean = this != Common.NON_ERROR