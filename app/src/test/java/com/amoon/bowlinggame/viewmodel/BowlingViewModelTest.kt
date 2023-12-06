package com.amoon.bowlinggame.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.amoon.bowlinggame.domain.usecase.DatabaseUseCase
import com.amoon.bowlinggame.presentation.viewModel.BowlingViewModel
import com.amoon.bowlinggame.utils.MockUtils
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.Assert.*
import org.mockito.Mockito.*

class BowlingViewModelTest {

    // This rule is used to make LiveData updates happen synchronously
    @get:Rule
    val rule = InstantTaskExecutorRule()

    // Use the UnconfinedTestDispatcher for testing Coroutines
    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var viewModel: BowlingViewModel

    // Mock dependencies
    private val mockDatabaseUseCase = mock(DatabaseUseCase::class.java)

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = BowlingViewModel(mockDatabaseUseCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `roll updates score and rolls correctly`() = runBlocking {
        // Given
        val userName = "Mehr"

        // When
        viewModel.roll(userName, "3,1")
        viewModel.roll(userName, "5,4")
        viewModel.roll(userName, "2")

        // Then
        assertEquals(15, viewModel.score.value)
        assertEquals(listOf("3,1", "5,4", "2"), viewModel.rolls.value)
    }

    @Test
    fun `roll updates score and rolls correctly, and inserts user score when game is finished`() =
        runBlocking {
            // Given
            val scoresEntities = MockUtils.mockScores
            val userName = "Mehr"
            val expectedRolls = (1..9).flatMap { listOf("3,3") } + listOf("3,4")

            // When
            repeat(9) { viewModel.roll(userName, "3,3") } // Rolls for the first 9 frames
            viewModel.roll(userName, "3,4") // 10th frame

            // Then
            assertEquals(61, viewModel.score.value)
            assertEquals(expectedRolls, viewModel.rolls.value)


            // Verify that insertUserScore is called when the game is finished
            verify(mockDatabaseUseCase, times(1)).insertScores(scoresEntities)

        }

}