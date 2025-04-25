package lotto.view

import camp.nextstep.edu.missionutils.Console
import lotto.error.*
import lotto.view.input.*
import lotto.view.util.printPrompt
import lotto.view.util.validateOrThrow

object InputView {

	fun parseWinningNumbersOrThrow(input: String): List<Int> =
		parseAndValidate(input, ::parseWinningNumbers, ::validateWinningNumbers)

	fun parseBonusNumberOrThrow(input: String): Int =
		parseAndValidate(input, ::parseBonusNumber, ::validateBonusNumber)

	fun parseAmountOrThrow(input: String): Long =
		parseAndValidate(input, ::parseAmount, ::validateAmount)

	fun readLineFromConsole(prompt: String): String {
		printPrompt(prompt)
		return Console.readLine()
			?: throw RetryInputException(ParseError.NULL_FOUND.toMessage())
	}

	private fun <T> parseAndValidate(
		input: String,
		parse: (String) -> T?,
		validate: (T) -> ErrorType
	): T {
		val notNullInput = input.validateOrThrow {
			if (it == null) ParseError.NULL_FOUND else Common.NON_ERROR
		}
		val parsed = parse(notNullInput)
			?: throw RetryInputException(ParseError.INVALID_FORMAT.toMessage())
		parsed.validateOrThrow(validate)
		return parsed
	}

}
