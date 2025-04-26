package lotto.util

import lotto.error.ErrorType
import lotto.error.RetryInputException
import lotto.error.isStatusFailure
import lotto.error.toMessage


internal inline fun <T> T.validateOrThrow(validation: (T) -> ErrorType): T {
	val error = validation(this)
	if (error.isStatusFailure()) {
		throw RetryInputException(error.toMessage())
	}
	return this
}
