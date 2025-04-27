package lotto

import lotto.error.UnexpectedException
import lotto.service.GameService

fun main() {

	try {
		GameService.start()
	} catch (e: UnexpectedException) {
		throw e
	}
}
