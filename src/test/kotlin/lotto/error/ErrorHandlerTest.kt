package lotto.error

import lotto.validator.Validator
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class ErrorHandlerTest {

	@Test
	fun `should throw RetryInputException when error is Winning NOT_IN_RANGE`() {
		assertThrows<RetryInputException> {
			ExceptionHandler.throwIf(WinningError.NOT_IN_RANGE)
		}
	}

	@Test
	fun `should throw UnexpectedException when error is Logic DEFAULT `() {
		assertThrows<UnexpectedException> {
			ExceptionHandler.throwIf(LogicError.DEFAULT)
		}
	}

	@Test
	fun `should not throw exception when error is Common NON_ERROR`() {
		assertDoesNotThrow {
			ExceptionHandler.throwIf(Common.NON_ERROR)
		}
	}

	@Test // flow-check, TODO: moved to separate flow test file later
	fun `should throw RetryInputException when winning numbers are out of range`() {
		val invalidWinningNumbers = listOf(0, 1, 2, 3, 4, 5)
		val error = Validator.checkWinningNumbers(invalidWinningNumbers)
		assertThrows<RetryInputException> {
			ExceptionHandler.throwIf(error)
		}
	}
}

