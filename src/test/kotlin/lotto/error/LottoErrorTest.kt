package lotto.error

import lotto.policy.LottoPolicy
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoErrorTest {

	// winning numbers

	@Test
	fun `should return correct message for NOT_IN_RANGE error`() {
		val error = LottoErrorTest.NOT_IN_RANGE
		assertThat(error.message)
			.contains("[ERROR]: All numbers must be between")
	}

	@Test
	fun `should return correct message for INVALID_SIZE error`() {
		val error = LottoError.INVALID_SIZE
		assertThat(error.message)
			.contains("exactly ${LottoPolicy.LOTTO_SIZE}")
	}

	@Test
	fun `should return correct message for DUPLICATE_NUMBER error`() {
		val error = LottoErrorTest.DUPLICATE_NUMBER
		assertThat(error.message)
			.contains("[ERROR]: Duplicate numbers are not allowed")
	}

	// bonus number

	@Test
	fun `should return correct message for BONUS_NOT_IN_RANGE error`() {
		val error = LottoErrorTest.BONUS_NOT_IN_RANGE
		assertThat(error.message)
			.contains("[ERROR]: Bonus number must be between")
	}

	@Test
	fun `should return correct message for DUPLICATE_BONUS_NUMBER error`() {
		val error = LottoErrorTest.DUPLICATE_BONUS_NUMBER
		assertThat(error.message)
			.contains("[ERROR]: Bonus number must not be one of")
	}
}