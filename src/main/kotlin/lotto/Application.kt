package lotto

import lotto.error.RetryInputException
import lotto.error.UnexpectedException
import lotto.service.GameService

fun main() {
	try {
		val gameResult = GameService.start()
		GameService.show(gameResult)
	} catch (e: RetryInputException) {
		println(e.message)
	} catch (e: UnexpectedException) {
		println("An unexpected error occurred: ${e.message}") // TODO: message should start with [ERROR]:
		return
	} catch (e: Exception) {
		println("Something went wrong: ${e.message}")  // TODO: message should start with [ERROR]:
		return
	}
}