package lotto.view

import lotto.error.RetryInputException
import lotto.error.UnexpectedException

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