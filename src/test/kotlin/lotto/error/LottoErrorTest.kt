package lotto.error

import lotto.policy.LottoPolicy
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoErrorTest {

	// winning numbers

	@Test
	fun `should return correct message for WINNING NOT_IN_RANGE`() {
		val error = LottoErrorMessage.of(Winning.NOT_IN_RANGE)
		assertThat(error)
			.contains("must be between")
			.contains("lotto numbers")
	}

	@Test
	fun `should return correct message for WINNING INVALID_SIZE`() {
		val error = LottoErrorMessage.of(Winning.INVALID_SIZE)
		assertThat(error)
			.contains("exactly")
			.contains(LottoPolicy.LOTTO_SIZE.toString())
	}

	@Test
	fun `should return correct message for WINNING DUPLICATE_NUMBER`() {
		val error = LottoErrorMessage.of(Winning.DUPLICATE_NUMBER)
		assertThat(error)
			.contains("must not be duplicated")
			.contains("numbers")
	}

	// bonus number

	@Test
	fun `should return correct message for BONUS NOT_IN_RANGE`() {
		val error = LottoErrorMessage.of(Bonus.NOT_IN_RANGE)
		assertThat(error)
			.contains("must be between")
			.contains("Bonus number")
	}

	@Test
	fun `should return correct message for BONUS DUPLICATE_NUMBER`() {
		val error = LottoErrorMessage.of(Bonus.DUPLICATE_NUMBER)
		assertThat(error)
			.contains("must not be duplicated")
			.contains("bonus")
	}

	@Test
	fun `should return correct message for PURCHASE NOT_DIVISIBLE_BY_TICKET_PRICE`() {
		val error = LottoErrorMessage.of(Purchase.NOT_DIVISIBLE_BY_TICKET_PRICE)
		assertThat(error)
			.contains("must be divisible")
			.contains("${LottoPolicy.TICKET_PRICE}")
	}

	@Test
	fun `should return correct message for PURCHASEINVALID_PURCHASE_AMOUNT`() {
		val error = LottoErrorMessage.of(Purchase.INVALID_PURCHASE_AMOUNT)
		assertThat(error)
			.contains("purchase amount must be")
			.contains("${LottoPolicy.TICKET_PRICE}")
	}
}
