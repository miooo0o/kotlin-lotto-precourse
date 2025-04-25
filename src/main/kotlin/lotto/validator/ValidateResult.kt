package lotto.validator

import lotto.error.*

/**
 * A result wrapper combining an ErrorType and a corresponding value.
 */
data class ValidationResult<T>(
	val errorType: ErrorType,
	val value: T?
) {
	fun isValid(): Boolean {
		if (errorType.isStatusFailure()) return false
		if (errorType.isStatusSuccess() && value != null) return true
		ExceptionHandler.throwIf(LogicError.INVALID_LOGIC)
		return false
	}

	fun toNullFree(): T {
		return value ?: throw UnexpectedException(LogicError.CONVERSION_FAILED.toMessage())
	}
}