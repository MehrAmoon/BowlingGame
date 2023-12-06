package com.amoon.bowlinggame.gameLogic

import com.amoon.bowlinggame.domain.game.BowlingGame
import org.junit.Assert.assertEquals
import org.junit.Test

class BowlingGameTest {

    @Test
    fun `test scoring with no strikes or spares`() {
        val game = BowlingGame()

        // Roll 4 in each frame
        repeat(20) { game.roll(4,4) }

        assertEquals(160, game.getScore())
    }

    @Test
    fun `test scoring with spares`() {
        val game = BowlingGame()

        // Roll a spare in every frame
        repeat(10) {
            game.roll(5)
            game.roll(5)
        }
        // Roll a 5 in the bonus roll
        game.roll(5)

        assertEquals(105, game.getScore())
    }

    @Test
    fun `test scoring with strikes`() {
        val game = BowlingGame()

        // Roll a strike in every frame
        repeat(10) {
            game.roll(10,0)
        }
        // Roll a 10 in each of the two bonus rolls
        game.roll(10,0,10)

        assertEquals(210, game.getScore())
    }


    @Test
    fun `test scoring with a mix of strikes, spares, and regular frames`() {
        val game = BowlingGame()

        // Roll a mix of strikes, spares, and regular frames
        game.roll(1,4)
        game.roll(4,5)
        game.roll(6,4)
        game.roll(5,5)
        game.roll(10,0)
        game.roll(0,1)
        game.roll(7,3)
        game.roll(6,4)
        game.roll(10,0)
        game.roll(2,8,6)

        assertEquals(133, game.getScore())
    }
}
