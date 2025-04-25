package lotto.view

import camp.nextstep.edu.missionutils.Console
import lotto.error.*
import lotto.policy.*

object InputView {

	fun readWinning(): List<Int> {
		val input = readInput(GamePolicy.WINNING_MESSAGE)
		val strings = parseStrings(input)
		return parseNumbers(strings)
	}

	// TODO
	fun readBonus(): List<Int> {
		val input = readInput(GamePolicy.WINNING_MESSAGE)
		val strings = parseStrings(input)
		return parseNumbers(strings)
	}

	// TODO
	fun readAmount(): List<Int> {
		val input = readInput(GamePolicy.WINNING_MESSAGE)
		val strings = parseStrings(input)
		return parseNumbers(strings)
	}

	private fun printPrompt(line: String) {
		println(line)
	}

	private fun readInput(line: String): String {
		printPrompt(line)
		return (Console.readLine())
			.validateOrThrow { if (it == null) ParseError.EMPTY_INPUT else Common.NON_ERROR }
			.trim()
	}

	private fun parseStrings(input: String): List<String> {
		return input.split(',')
			.map { it.trim() }
			.validateOrThrow { it.containsOnlyNumeric() }
	}

	private fun parseNumbers(strings: List<String>): List<Int> {
		return strings
			.map { it.toInt() }
			.validateOrThrow {
				listOf(
					it.hasValidSize(),
					it.hasNoDuplicates(),
					it.isInValidRange(),
				).firstOrNull { error -> error.isStatusFailure() } ?: Common.NON_ERROR
			}
	}

	private inline fun <T> T.validateOrThrow(validation: (T) -> ErrorType): T {
		val error = validation(this)
		if (error.isStatusFailure()) {
			throw RetryInputException(error.toMessage())
		}
		return this
	}
}
