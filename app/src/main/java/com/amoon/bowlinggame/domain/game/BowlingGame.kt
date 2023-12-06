package com.amoon.bowlinggame.domain.game

// Dependency Inversion Principle (DIP):
// - The BowlingGame class depends on abstractions (FrameHandler interface) rather than concrete implementations.
// - The FrameHandler instances are injected into the BowlingGame class.
class BowlingGame(
    private val frameHandlers: List<FrameHandler> = listOf(
        RegularFrameHandler(),
        TenthFrameHandler()
    )
) {

    private val frames = mutableListOf<Frame>()
    private var currentFrame = 0
    private var currentRoll = 0

    fun getCurrentFrame(): Int = currentFrame + 1

    fun roll(roll1: Int, roll2: Int = 0, roll3: Int = 0) {

        // Add rolls
        frames.add(Frame(roll1, roll2))

        // Calculate sum of rolls
        val sumRolls = roll1 + roll2

        // Handle bonuses using injected FrameHandler instances
        frameHandlers.forEach { it.handleBonuses(frames, currentFrame, roll1, roll3, sumRolls) }

        // Move to the next roll or frame
        if (currentRoll == 1 || currentFrame == 10) {
            currentRoll = 0
            currentFrame++
        } else {
            currentFrame++
            currentRoll++
        }
    }

    fun getScore(): Int {
        var totalScore = 0
        for (i in 0 until frames.size) {
            val frame = frames[i]
            totalScore += frame.getTotalScore()
        }
        return totalScore
    }

}