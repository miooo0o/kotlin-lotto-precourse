package lotto.error

class RetryInputException(message: String) : IllegalArgumentException(message) {
}

class UnexpectedException(message: String) : IllegalStateException(message) {
}

object ExceptionHandler {
	fun throwIf(errorType: ErrorType) {
		if (errorType == Common.NON_ERROR) return
		if (errorType.isTypeRetry()) throw RetryInputException(errorType.toMessage())
	}
}