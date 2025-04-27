package lotto

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class LottoTest {
	@Test
	fun `throws an exception when lotto numbers exceed six`() {
		assertThrows<IllegalArgumentException> {
			Lotto(listOf(1, 2, 3, 4, 5, 6, 7))
		}
	}

	@Test
	fun `throws an exception when lotto numbers contain duplicates`() {
		assertThrows<IllegalArgumentException> {
			Lotto(listOf(1, 2, 3, 4, 5, 5))
		}
	}

	@Test
	fun `creates lotto successfully with valid numbers`() {
		assertDoesNotThrow {
			Lotto(listOf(1, 2, 3, 4, 5, 6))
		}
	}

	@Test
	fun `throws an exception when lotto numbers are less than six`() {
		assertThrows<IllegalArgumentException> {
			Lotto(listOf(1, 2, 3, 4, 5)) // 5개
		}
	}

	@Test
	fun `throws an exception when lotto numbers are out of valid range`() {
		assertThrows<IllegalArgumentException> {
			Lotto(listOf(0, 1, 2, 3, 4, 5))
		}
		assertThrows<IllegalArgumentException> {
			Lotto(listOf(1, 2, 3, 4, 5, 46))
		}
	}

	@Test
	fun `throws an exception when lotto numbers have multiple duplicates`() {
		assertThrows<IllegalArgumentException> {
			Lotto(listOf(2, 2, 2, 2, 2, 2))
		}
	}
}
