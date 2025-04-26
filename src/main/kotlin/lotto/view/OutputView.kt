package lotto.view

import lotto.domain.Lotto

fun List<Lotto>.viewPurchasedTickets() {
	forEach { it.display() }
}
