package lotto.error

class RetryInputException(message: String) : IllegalArgumentException(message) {
}

class UnexpectedException(message: String) : IllegalStateException(message) {
}

class UnreachableCodeException(message: String) : IllegalStateException(message) {
}

object ExceptionHandler {
	fun throwIf(errorType: ErrorType) {
		if (errorType == Common.NON_ERROR) return
		if (errorType.isTypeRetry()) throw RetryInputException(errorType.toMessage())
		if (errorType.isTypeLogic()) throw UnexpectedException(errorType.toMessage())
	}
}