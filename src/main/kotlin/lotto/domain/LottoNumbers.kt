package lotto.domain

import camp.nextstep.edu.missionutils.Randoms
import lotto.policy.GamePolicy

object LottoNumbers {
	fun generate(): List<Int> {
		return Randoms.pickUniqueNumbersInRange(
			GamePolicy.LOTTO_MIN_NUMBER,
			GamePolicy.LOTTO_MAX_NUMBER,
			GamePolicy.LOTTO_SIZE
		).sorted()
	}
}
