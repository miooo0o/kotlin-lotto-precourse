package lotto.view

import lotto.error.*
import lotto.validator.Validator


data class ValidationResult<T>(
	val errorType: ErrorType,
	val value: T?
) {
	fun isValid(): Boolean {
		if (errorType.isStatusSuccess() && value != null) return true
		if (errorType.isStatusFailure() && value == null) return false
		ExceptionHandler.throwIf(LogicError.INVALID_LOGIC)
		return false
	}

	fun toNullFree(): T {
		return value ?: throw UnexpectedException(LogicError.CONVERSION_FAILED.toMessage())
	}

}

object InputParser {

	fun toAmount(input: String): Long {
		val parsedAmount = toStringToAmount(input)
		if (parsedAmount.isValid()) {
			val validatedAmount = parsedAmount.toNullFree()
			val errorType = Validator.checkAmount(validatedAmount)
			if (errorType.isStatusSuccess()) {
				return validatedAmount
			}
			ExceptionHandler.throwIf(errorType)
		}
		throw UnexpectedException(LogicError.DEFAULT.toMessage())
	}

	private fun toStringToAmount(input: String): ValidationResult<Long> {
		val numericStatus = Validator.checkDigit(input)
		if (numericStatus.isStatusFailure()) {
			return ValidationResult(numericStatus, null)
		}

		val amount = input.toLongOrNull()
		val toLongStatus = Validator.checkLongValue(amount)
		return ValidationResult(toLongStatus, amount)
	}
}

