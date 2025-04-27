package lotto.view

import lotto.domain.Lotto
import lotto.policy.GamePolicy
import lotto.util.requireOrUnexpected

fun List<Lotto>.viewPurchasedTickets() {
	requireOrUnexpected(this.size < GamePolicy.MIN_TICKET_SIZE) {
		"The registered lotto must be ${GamePolicy.MIN_TICKET_SIZE} or more"
	}

	println("You have purchased ${this.size} tickets.")
	forEach { it.display() }
	println()
}

object OutputView {
	fun viewPurchasedTickets(lottos: List<Lotto>) {
		lottos.viewPurchasedTickets()
	}
}