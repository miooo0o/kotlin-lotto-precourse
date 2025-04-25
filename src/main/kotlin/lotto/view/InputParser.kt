package lotto.view

import lotto.error.*
import lotto.validator.ValidationResult
import lotto.validator.Validator

object InputParser {

	fun toAmount(input: String): Long {
		val validationResult = fromStringToAmount(input)
		if (validationResult.isValid()) {
			val validatedAmount = validationResult.toNullFree()
			val error = Validator.checkAmount(validatedAmount)
			if (error.isStatusSuccess()) {
				return validatedAmount
			}
			ExceptionHandler.throwIf(error)
		}
		ExceptionHandler.throwIf(validationResult.errorType)
		throw UnreachableCodeException("This code should not be reachable")
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
			val error = Validator.checkWinningNumbers(list)
			if (error.isStatusSuccess()) {
				return list
			}
			ExceptionHandler.throwIf(error)
		}
		ExceptionHandler.throwIf(validationResult.errorType)
		throw UnreachableCodeException("This code should not be reachable")
	}

	// TODO: refactor: to small functions
	private fun fromStringToWinning(input: String): ValidationResult<List<Int>> {
		val winningStrings = parseCommaSeparatedStrings(input)
		val error = Validator.checkNumeric(winningStrings)
		if (error.isStatusFailure())
			return ValidationResult(error, null)
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
		return parsed
	}
}

