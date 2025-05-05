package lotto

fun main() {
	val amount = InputView.read(AmountUntil())
	val lottoMachine = LottoMachine()
	val listOfLotto = lottoMachine.asMuch(amount)
	OutputView.displayListOfLotto(listOfLotto)

	val winningNumbers = InputView.read(WinningNumberUntil())
	val bonusNumber = InputView.read(BonusUntil(), winningNumbers)

	try {
		val result = Analyze.result(listOfLotto, winningNumbers, bonusNumber)
		OutputView.displayResults(result, amount)

	} catch (e: IllegalArgumentException) {
		println("[ERROR]: ${e.message}")
	}
}
