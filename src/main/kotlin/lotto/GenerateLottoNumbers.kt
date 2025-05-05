package lotto

import camp.nextstep.edu.missionutils.Randoms

interface Generator {
	fun generate(): List<Int>
}

object GenerateLottoNumbers : Generator {
	override fun generate(): List<Int> = Randoms.pickUniqueNumbersInRange(1, 45, 6)
}

object GenerateWrongNumbers : Generator {
	override fun generate(): List<Int> = Randoms.pickUniqueNumbersInRange(-10, 0, 6)
}