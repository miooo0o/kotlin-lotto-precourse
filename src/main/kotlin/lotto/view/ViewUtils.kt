package lotto.view

import lotto.error.*

fun printPrompt(line: String) {
	println(line)
}

fun <T> repeatUntilValid(
	prompt: String,
	reader: (String) -> T,
	validator: (T) -> ErrorType = { Common.NON_ERROR }
): T {
	while (true) {
		try {
			return parseInput(readUserInput(prompt), reader)
				.also { validateInput(it, validator) }
		} catch (e: RetryInputException) {
			println(e.message + "\n")
		} catch (e: Exception) {
			throw e
		}
	}
}

private fun readUserInput(prompt: String): String {
	return InputView.readLineFromConsole(prompt)
}

private fun <T> parseInput(input: String, reader: (String) -> T): T {
	return reader(input)
}

private fun <T> validateInput(value: T, validator: (T) -> ErrorType) {
	val error = validator(value)
	if (error.isStatusFailure()) {
		throw RetryInputException(error.toMessage())
	}
}
