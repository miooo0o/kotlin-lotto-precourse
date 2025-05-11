package box

// 13:20 ~

enum class ValidateLevel { SAFE, INVALID }

object OutputView {
	fun displayAllBoxes(allBoxes: Map<Int, List<String>>) {
		allBoxes.forEach { displayEachBox(it) }
	}

	private fun displayEachBox(boxAndItems: Map.Entry<Int, List<String>>) {
		val gradeLevel = gradeEachBox(boxAndItems.value)
		val message = when (gradeLevel.first) {
			ValidateLevel.SAFE -> "Grade: SAFE"
			ValidateLevel.INVALID -> "missing: ${gradeLevel.second.joinToString()}"
		}
		println("box ${boxAndItems.key}: $message")
	}

	private fun gradeEachBox(items: List<String>): Pair<ValidateLevel, List<String>> {
		val needsBubbleWrap = listOf("fragile", "glass").any { it in items }
		val hasElectronics = "electronics" in items
		val missing = mutableListOf<String>()

		if ("label" !in items) missing.add("label")
		if (needsBubbleWrap && "bubblewrap" !in items) missing.add("bubblewrap for fragile/glass items")
		if (hasElectronics && "certificate" !in items) missing.add("certificate for electronics")

		return when {
			missing.isEmpty() -> ValidateLevel.SAFE to emptyList()
			else -> ValidateLevel.INVALID to missing
		}
	}
}