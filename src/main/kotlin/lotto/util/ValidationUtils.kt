package lotto.util

import lotto.error.*

internal inline fun <T> T.validateOrThrow(validation: (T) -> ErrorType): T {
	val error = validation(this)
	if (error.isStatusFailure()) {
		throw RetryInputException(error.toMessage())
	}
	return this
}

internal inline fun requireOrUnexpected(condition: Boolean, lazyMessage: () -> String) {
	if (!condition) {
		throw UnexpectedException(lazyMessage())
	}
}