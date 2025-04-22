package lotto.policy

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ValidatorTest {
	@Test
	fun `throws exception for lotto numbers with more than 6 elements`() {
		val invalidNumbers = listOf(1, 2, 3, 4, 5, 6, 7)
		val exception = assertThrows<IllegalArgumentException> {
			Validator.isValidLottoNumbers(invalidNumbers)
		}
		assertThat(exception.message)
			.contains("exactly 6")
			.contains("given: 7")
	}

	@Test
	fun `throws exception for lotto numbers with less than 6 elements`() {
		val invalidNumbers = listOf(1, 2, 3, 4, 5)
		val exception = assertThrows<IllegalArgumentException> {
			Validator.isValidLottoNumbers(invalidNumbers)
		}
		assertThat(exception.message)
			.contains("exactly 6")
			.contains("given: 5")
	}

	@Test
	fun `pass when lotto numbers are valid`() {
		val validNumbers = listOf(1, 2, 3, 4, 5, 6)
		Validator.isValidLottoNumbers(validNumbers)
	}

	@Test
	fun `throws exception when lotto numbers contain duplicates`() {
		val invalidNumbers = listOf(1, 2, 3, 4, 5, 5)
		val exception = assertThrows<IllegalArgumentException> {
			Validator.isValidLottoNumbers(invalidNumbers)
		}
		assertThat(exception.message)
			.contains("[ERROR]: Duplicate numbers are not allowed.")
	}

	@Test
	fun `throws exception when lotto numbers are out of range (too small)`() {
		val invalidNumbers = listOf(-1, 1, 2, 3, 4, 5)
		val exception = assertThrows<IllegalArgumentException> {
			Validator.isValidLottoNumbers(invalidNumbers)
		}
		assertThat(exception.message)
			.contains("[ERROR]: All numbers must be between")
	}

	@Test
	fun `throws exception when lotto numbers are out of range (too big)`() {
		val invalidNumbers = listOf(401, 1, 2, 3, 4, 5)
		val exception = assertThrows<IllegalArgumentException> {
			Validator.isValidLottoNumbers(invalidNumbers)
		}
		assertThat(exception.message)
			.contains("[ERROR]: All numbers must be between")
	}

	@Test
	fun `returns false when bonus number is duplicated in winning numbers`() {
		val winningNumbers = listOf(1, 2, 3, 4, 5, 6)
		val duplicateBonus = 1
		val result = Validator.isValidBonusNumber(duplicateBonus, winningNumbers)
		assertThat(result).isFalse()
	}

	@Test
	fun `returns true when bonus number is in valid range and not duplicated`() {
		val winningNumbers = listOf(1, 2, 3, 4, 5, 6)
		val bonus = 7
		val result = Validator.isValidBonusNumber(bonus, winningNumbers)
		assertThat(result).isTrue()
	}

	@Test
	fun `returns false when amount is not divisible`() {
		val invalidAmountNotDivisible = 124034913401
		val result = Validator.isValidPurchaseAmount(invalidAmountNotDivisible)
		assertThat(result).isFalse()
	}

	@Test
	fun `returns true when amount is divisible`() {
		val divisibleAmount = 120000000000
		val result = Validator.isValidPurchaseAmount(divisibleAmount)
		assertThat(result).isTrue()
	}

	@Test
	fun `returns false when amount too low`() {
		val tooLowAmount = 999L
		val result = Validator.isValidPurchaseAmount(tooLowAmount)
		assertThat(result).isFalse()
	}
}