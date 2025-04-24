package lotto.input

//import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import lotto.error.ExceptionHandler
import lotto.error.RetryInputException
import lotto.error.isStatusSuccess
import lotto.policy.GamePolicy
import lotto.view.InputParser
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class InputViewTest {
	@Test
	fun `should parse valid amount string to Long`() {
		val inputAmount = (GamePolicy.TICKET_PRICE * 70L).toString()
		val parserResult = InputParser.toAmount(inputAmount)
		parserResult.errorType.isStatusSuccess()
		assertNotNull(parserResult.value)
	}


	@Test
	fun `should throw RetryInputException when amount is not a number`() {
		val notNumeric = "!!!!!!!!!!!!!"
		val parserResult = InputParser.toAmount(notNumeric)
		assertThrows<RetryInputException> {
			ExceptionHandler.throwIf(parserResult.errorType)
		}
	}

	@Test
	fun `should return list of Int from valid comma-separated numbers`() {
	}
}