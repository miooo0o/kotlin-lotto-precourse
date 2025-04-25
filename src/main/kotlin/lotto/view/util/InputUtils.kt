package lotto.view.util

import lotto.error.*
import lotto.view.InputView

internal inline fun <T> T.validateOrThrow(validation: (T) -> ErrorType): T {
	val error = validation(this)
	if (error.isStatusFailure()) {
		throw RetryInputException(error.toMessage())
	}
	return this
}

fun <T> repeatUntilValid(
	prompt: String,
	reader: (String) -> T
): T {
	while (true) {
		try {
			val input = InputView.readLineFromConsole(prompt)
			return reader(input)
		} catch (e: RetryInputException) {
			println(e.message)
		} catch (e: UnexpectedException) {
			throw e
		}
	}
}

fun printPrompt(line: String) {
	println(line)
}