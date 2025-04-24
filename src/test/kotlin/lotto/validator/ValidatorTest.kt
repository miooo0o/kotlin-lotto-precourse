package lotto.validator

import lotto.error.BonusError
import lotto.error.PurchaseError
import lotto.error.WinningError
import lotto.policy.GamePolicy
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class ValidatorTest {
	@Test
	fun `returns INVALID_SIZE error when lotto numbers have more than 6 elements`() {
		val invalidSizeWinning = listOf(1, 2, 3, 4, 5, 6, 7)
		val errorType = Validator.checkWinningNumbers(invalidSizeWinning)
		Assertions.assertThat(errorType).isEqualTo(WinningError.INVALID_SIZE)
	}

	@Test
	fun `returns INVALID_SIZE error when lotto numbers have less than 6 elements`() {
		val invalidSizeWinning = listOf(1, 2, 3, 4, 5)
		val errorType = Validator.checkWinningNumbers(invalidSizeWinning)
		Assertions.assertThat(errorType).isEqualTo(WinningError.INVALID_SIZE)
	}

	@Test
	fun `returns null when lotto numbers are valid`() {
		val winning = listOf(1, 2, 3, 4, 5, 6)
		val errorType = Validator.checkWinningNumbers(winning)
		Assertions.assertThat(errorType).isEqualTo(null)
	}

	@Test
	fun `returns DUPLICATE_NUMBER error when lotto numbers contain duplicates`() {
		val duplicatesWinning = listOf(1, 2, 3, 4, 5, 5)
		val errorType = Validator.checkWinningNumbers(duplicatesWinning)
		Assertions.assertThat(errorType).isEqualTo(WinningError.DUPLICATE_NUMBER)
	}

	@Test
	fun `returns NOT_IN_RANGE error when lotto numbers are not in range (too small)`() {
		val outOfRangeWinning = listOf(-1, 0, 1, 2, 3, 4)
		val errorType = Validator.checkWinningNumbers(outOfRangeWinning)
		Assertions.assertThat(errorType).isEqualTo(WinningError.NOT_IN_RANGE)
	}

	@Test
	fun `returns NOT_IN_RANGE error when lotto numbers are not in range (too big)`() {
		val outOfRangeWinning = listOf(2021, 401, 1, 2, 3, 4)
		val errorType = Validator.checkWinningNumbers(outOfRangeWinning)
		Assertions.assertThat(errorType).isEqualTo(WinningError.NOT_IN_RANGE)
	}

	@Test
	fun `returns DUPLICATE_NUMBER error when bonus number is duplicated in winning numbers`() {
		val winning = listOf(1, 2, 3, 4, 5, 6)
		val bonus = winning[0]
		val errorType = Validator.checkBonusNumber(bonus, winning)
		Assertions.assertThat(errorType).isEqualTo(BonusError.DUPLICATE_NUMBER)
	}

	@Test
	fun `returns NOT_IN_RANGE error when bonus number is not in range`() {
		val winning = listOf(1, 2, 3, 4, 5, 6)
		val bonus = 4242
		val errorType = Validator.checkBonusNumber(bonus, winning)
		Assertions.assertThat(errorType).isEqualTo(BonusError.NOT_IN_RANGE)
	}


	@Test
	fun `returns null when bonus number is in valid range and not duplicated`() {
		val winning = listOf(1, 2, 3, 4, 5, 6)
		val bonus = winning[0]
		val errorType = Validator.checkBonusNumber(bonus, winning)
		Assertions.assertThat(errorType).isEqualTo(BonusError.DUPLICATE_NUMBER)
	}


	@Test
	fun `should return NOT_DIVISIBLE_BY_TICKET_PRICE error when amount is not multiple of ticket price`() {
		val amountNotDivisibleByTicketPrice = GamePolicy.TICKET_PRICE * 123 + 1L
		val errorType = Validator.checkAmount(amountNotDivisibleByTicketPrice)
		Assertions.assertThat(errorType).isEqualTo(PurchaseError.NOT_DIVISIBLE_BY_TICKET_PRICE)
	}

	@Test
	fun `should return INVALID_PURCHASE_AMOUNT error when amount is not multiple of ticket price`() {
		val amountNotPurchase = GamePolicy.TICKET_PRICE - 42L
		val errorType = Validator.checkAmount(amountNotPurchase)
		Assertions.assertThat(errorType).isEqualTo(PurchaseError.CANNOT_AFFORD_TICKET)
	}
}