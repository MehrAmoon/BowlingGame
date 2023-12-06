package com.amoon.bowlinggame.presentation.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amoon.bowlinggame.data.database.entities.ScoresEntity
import com.amoon.bowlinggame.domain.game.BowlingGame
import com.amoon.bowlinggame.domain.usecase.DatabaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BowlingViewModel @Inject constructor(
    private val databaseUseCase: DatabaseUseCase
) : ViewModel() {

    private val bowlingGame = BowlingGame()

    private val _rolls = mutableStateOf<List<String>>(emptyList())
    val rolls: State<List<String>> = _rolls

    private val _score = mutableStateOf(0)
    val score: State<Int> = _score

    private var isGameFinished = false

    fun roll(userName: String, pin: String) {
        if (!isGameFinished) {
            val pins = pin.split(",")
            bowlingGame.roll(
                pins.getOrNull(0)?.toIntOrNull() ?: 0,
                pins.getOrNull(1)?.toIntOrNull() ?: 0,
                pins.getOrNull(2)?.toIntOrNull() ?: 0
            )

            _score.value = bowlingGame.getScore()
            _rolls.value = _rolls.value + pin

            // Check if the game has reached the maximum number of frames
            if (bowlingGame.getCurrentFrame() == 11) {
                isGameFinished = true
                insertUserScore(
                    ScoresEntity(
                        user_name = userName,
                        score = _score.value,
                        rolls = _rolls.value.toString()
                    )
                )
            }
        }
    }

    private fun insertUserScore(userScore: ScoresEntity) {
        viewModelScope.launch {
            databaseUseCase.insertScores(userScore)
        }
    }

    fun isGameFinished(): Boolean = isGameFinished

}
