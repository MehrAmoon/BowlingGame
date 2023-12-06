package com.amoon.bowlinggame.domain.game

// Single Responsibility Principle (SRP)
// - Each class or interface has a single responsibility related to frame handling.
// - FrameHandler interface defines a single method for handling bonuses.
interface FrameHandler {
    fun handleBonuses(
        frameList: List<Frame>,
        currentFrameIndex: Int,
        roll1: Int,
        roll3: Int,
        sumRolls: Int)
}


// Open/Closed Principle (OCP)
// - The code is open for extension but closed for modification.
// - New frame types can be added by creating new implementations of the FrameHandler interface.
class RegularFrameHandler : FrameHandler {
    override fun handleBonuses(frameList: List<Frame>, currentFrameIndex: Int, roll1: Int, roll3: Int, sumRolls: Int) {
        if (currentFrameIndex > 0) {
            val prevFrame = frameList[currentFrameIndex - 1]
            if (prevFrame.isSpare()) {
                prevFrame.bonus += roll1
            } else if (prevFrame.isStrike()) {
                prevFrame.bonus += sumRolls
            }
        }
    }
}

class TenthFrameHandler : FrameHandler {
    override fun handleBonuses(frameList: List<Frame>, currentFrameIndex: Int,  roll1: Int, roll3: Int, sumRolls: Int) {
        if (currentFrameIndex == 9) {
            val current = frameList[currentFrameIndex]
            if (current.isSpare() || current.isStrike()) {
                // Allow a third roll if the second roll is a strike
                // Handle special cases for the 10th frame
                current.roll3 = roll3
            }
        }
    }
}