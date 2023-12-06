package com.amoon.bowlinggame.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.amoon.bowlinggame.data.database.ScoresDao
import com.amoon.bowlinggame.data.repository.DatabaseRepositoryImpl
import com.amoon.bowlinggame.utils.MockUtils.mockScores
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flowOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.*
import java.util.concurrent.CountDownLatch
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalTime
class DatabaseRepositoryTest {

    private lateinit var repository: DatabaseRepositoryImpl
    private lateinit var dao: ScoresDao

    // This rule swaps the background executor used by the Architecture Components
    // with a different one which executes each task synchronously.
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        dao = mockk()
        repository = DatabaseRepositoryImpl(dao)
    }

    @Test
    fun `getAllScores should return list of scores`() = runBlocking {
        // Given
        val scoresEntities = listOf(mockScores)

        coEvery { dao.getAllUserScores() } returns flowOf(scoresEntities)

        // When
        val result = repository.getAllScores()

        // Then
        result.collect { scores ->
            assertEquals(scoresEntities, scores)
        }
    }



    @Test
    fun `insertScores should call dao insertScore and then return what we inserted`() = runBlocking {
        // Given
        val scoresEntity = mockScores

        coEvery { dao.insertScore(scoresEntity) } returns Unit

        // When insert score
        repository.insertScores(scoresEntity)

        coEvery { dao.getAllUserScores() } returns flowOf(listOf(scoresEntity) )

        // When get all scores
        val result = repository.getAllScores()

        // Then
        result.collect { scores ->
            assertEquals(listOf(scoresEntity) , scores)
        }
    }

}
