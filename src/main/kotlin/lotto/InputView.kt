package lotto

object InputView {

	fun <T> read(condition: ReadUntil<T>): T {
		while (true) {
			try {
				return condition.answer()
			} catch (e: IllegalArgumentException) {
				println("[ERROR]: ${e.message}")
			}
		}
	}

	fun read(condition: BonusUntil, winningNumbers: List<Int>): Int {
		while (true) {
			try {
				val answered = condition.answer()
				condition.validateDuplicate(answered, winningNumbers)
				return answered
			} catch (e: IllegalArgumentException) {
				println("[ERROR]: ${e.message}")
			}
		}
	}
}