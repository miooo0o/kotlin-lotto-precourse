package lotto.error

import lotto.policy.GamePolicy
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ErrorFormatterTest {

	// winning numbers

	@Test
	fun `should return correct message for WINNING NOT_IN_RANGE`() {
		val error = ErrorFormatter.of(Winning.NOT_IN_RANGE)
		assertThat(error)
			.contains("must be between")
			.contains("lotto numbers")
	}

	@Test
	fun `should return correct message for WINNING INVALID_SIZE`() {
		val error = ErrorFormatter.of(Winning.INVALID_SIZE)
		assertThat(error)
			.contains("exactly")
			.contains(GamePolicy.LOTTO_SIZE.toString())
	}

	@Test
	fun `should return correct message for WINNING DUPLICATE_NUMBER`() {
		val error = ErrorFormatter.of(Winning.DUPLICATE_NUMBER)
		assertThat(error)
			.contains("must not be duplicated")
			.contains("numbers")
	}

	// bonus number

	@Test
	fun `should return correct message for BONUS NOT_IN_RANGE`() {
		val error = ErrorFormatter.of(Bonus.NOT_IN_RANGE)
		assertThat(error)
			.contains("must be between")
			.contains("bonus number")
	}

	@Test
	fun `should return correct message for BONUS DUPLICATE_NUMBER`() {
		val error = ErrorFormatter.of(Bonus.DUPLICATE_NUMBER)
		assertThat(error)
			.contains("must not be duplicated")
			.contains("bonus")
	}

	@Test
	fun `should return correct message for PURCHASE NOT_DIVISIBLE_BY_TICKET_PRICE`() {
		val error = ErrorFormatter.of(Purchase.NOT_DIVISIBLE_BY_TICKET_PRICE)
		assertThat(error)
			.contains("must be divisible")
			.contains("${GamePolicy.TICKET_PRICE}")
	}

	@Test
	fun `should return correct message for PURCHASEINVALID_PURCHASE_AMOUNT`() {
		val error = ErrorFormatter.of(Purchase.INVALID_PURCHASE_AMOUNT)
		assertThat(error)
			.contains("purchase amount must be")
			.contains("${GamePolicy.TICKET_PRICE}")
	}
}
