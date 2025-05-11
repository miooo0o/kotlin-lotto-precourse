package box

import camp.nextstep.edu.missionutils.Console

// 12:30 ~ 13:16
val VALID_ITEMS = setOf(
	"fragile", "glass", "bubblewrap", "electronics", "label", "clothes", "knife", "certificate"
)


object InputView {
	fun readBoxesAmount(): Int {
		return retryTillValidate(
			ask = { println("how many box(s) do you need?") },
			read = { Console.readLine()?.trim()?.toIntOrNull() },
			validate = { require(it > 0) { "read: at least 1 box need to confirm!" } },
		)
	}

	fun readItemsInBoxes(amount: Int): Map<Int, List<String>> {
		val boxesWithItems = mutableMapOf<Int, List<String>>()
		for (i in 0 until amount) {
			val items = readItemsForEachBox(i, amount)
			boxesWithItems[i] = items
		}
		return boxesWithItems
	}

	private fun readItemsForEachBox(index: Int, amount: Int): List<String> {
		return retryTillValidate(
			ask = { println("Box ${index + 1} / $amount: ") },
			read = {
				Console.readLine()
					?.split(",")
					?.map { it.trim() }
					?.filter { it.isNotBlank() }
			},
			validate = {
				require(it.isNotEmpty()) { "read: list cannot be empty" }
				require(it.all { item -> item in VALID_ITEMS }) {
					"read: found invalid item(s): ${it.filter { item -> item !in VALID_ITEMS }}"
				}
			},
		)
	}

	private fun <T> retryTillValidate(
		ask: () -> Unit,
		read: () -> T?,
		validate: (T) -> Unit
	): T {
		while (true) {
			try {
				ask()
				val input = read() ?: throw IllegalArgumentException("can not read")
				validate(input)
				return input
			} catch (e: Exception) {
				println("[ERROR]: ${e.message}")
			}
		}
	}
}

