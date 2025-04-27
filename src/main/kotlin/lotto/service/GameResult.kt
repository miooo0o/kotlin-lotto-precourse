package lotto.service

import lotto.domain.Lotto

data class GameResult(
	val lottoList: List<Lotto>,
	val winningNumbers: List<Int>,
	val bonusNumber: Int,
	val amount: Long
)