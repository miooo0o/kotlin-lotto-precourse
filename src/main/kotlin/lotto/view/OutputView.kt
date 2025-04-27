package lotto.view

import lotto.domain.Lotto

fun List<Lotto>.viewPurchasedTickets() {
	println("You have purchased ${this.size} tickets.")
	forEach { it.display() }
	println()
}