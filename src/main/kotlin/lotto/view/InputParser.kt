package lotto.view

import lotto.error.*
import lotto.validator.Validator


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

object InputParser {

	fun toAmount(input: String): Long {
		val validationResult = fromStringToAmount(input)
		if (validationResult.isValid()) {
			val validatedAmount = validationResult.toNullFree()
			val validateStatus = Validator.checkAmount(validatedAmount)
			if (validateStatus.isStatusSuccess()) {
				return validatedAmount
			}
			ExceptionHandler.throwIf(validateStatus)
		}
		throw UnexpectedException(LogicError.DEFAULT.toMessage())
	}

	private fun fromStringToAmount(input: String): ValidationResult<Long> {
		val numericStatus = Validator.checkDigit(input)
		if (numericStatus.isStatusFailure()) {
			return ValidationResult(numericStatus, null)
		}

		val amount = input.toLongOrNull()
		val toLongStatus = Validator.checkLongValue(amount)
		return ValidationResult(toLongStatus, amount)
	}

	fun toWinning(input: String): List<Int> {
		val validationResult = fromStringToWinning(input)
		if (validationResult.isValid()) {
			val list = validationResult.toNullFree()
			val validateStatus = Validator.checkWinningNumbers(list)
			if (validateStatus.isStatusSuccess()) {
				return list
			}
			ExceptionHandler.throwIf(validateStatus)
		}
		throw UnexpectedException(LogicError.DEFAULT.toMessage())
	}

	// TODO: refactor: to small functions
	private fun fromStringToWinning(input: String): ValidationResult<List<Int>> {
		val winningStrings = parseCommaSeparatedStrings(input)
		val digitStatus = Validator.checkAllDigit(winningStrings)
		if (digitStatus.isStatusFailure())
			return ValidationResult(digitStatus, null)
		val listOrNull = winningStrings.map { it.toIntOrNull() }
		if (listOrNull.any { it == null }) {
			return ValidationResult(ParseError.INVALID_RANGE, null)
		}
		val list = listOrNull.filterNotNull()
		if (list.isNotEmpty()) {
			return ValidationResult(Common.NON_ERROR, list)
		}
		return ValidationResult(LogicError.CONVERSION_FAILED, null)
	}

	private fun parseCommaSeparatedStrings(input: String): List<String> {
		val parsed = input.split(',')
			.map { it.trim() }
		return (parsed)
	}
}

