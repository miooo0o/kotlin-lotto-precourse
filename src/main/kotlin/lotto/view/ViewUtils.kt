package lotto.view

import lotto.error.*

fun <T> repeatUntilValid(
	prompt: String,
	reader: (String) -> T,
	validator: (T) -> ErrorType = { Common.NON_ERROR }
): T {
	while (true) {
		try {
			val input = InputView.readLineFromConsole(prompt)
			val value = reader(input)
			val error = validator(value)
			if (error.isStatusFailure()) {
				throw RetryInputException(error.toMessage())
			}
			return value
		} catch (e: RetryInputException) {
			println(e.message + "\n")
		} catch (e: UnexpectedException) {
			throw e
		}
	}
}

fun printPrompt(line: String) {
	println(line)
}