package com.amoon.bowlinggame.domain.game


data class Frame(
    var roll1: Int,
    var roll2: Int = 0,
    var roll3: Int = 0,
    var bonus: Int = 0
) {
    fun getTotalScore(): Int = roll1 + roll2 + roll3 + bonus
    fun isSpare(): Boolean = roll1 + roll2 == 10 && roll1 != 10
    fun isStrike(): Boolean = roll1 == 10
}