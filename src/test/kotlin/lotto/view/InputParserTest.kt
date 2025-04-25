package lotto.view

import lotto.error.RetryInputException
import lotto.policy.GamePolicy
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class InputParserTest {

	@Test
	fun `should parse valid amount string to Long`() {
		val inputAmount = (GamePolicy.TICKET_PRICE * 70L).toString()
		val parserResult = InputParser.toAmount(inputAmount)
		Assertions.assertNotNull(parserResult)
	}


	@Test
	fun `should throw RetryInputException when amount is not a number`() {
		val notNumeric = "!!!!!!!!!!!!!"
		assertThrows<RetryInputException> {
			InputParser.toAmount(notNumeric)
		}
	}

	@Test
	fun `should return list of Int from valid comma-separated numbers`() {
	}
}