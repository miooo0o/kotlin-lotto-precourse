package box

fun main() {
	val boxAmount = InputView.readBoxesAmount()
	val boxesAndItems = InputView.readItemsInBoxes(boxAmount)
	OutputView.displayAllBoxes(boxesAndItems)
}