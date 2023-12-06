package com.amoon.bowlinggame.domain.repository

import com.amoon.bowlinggame.data.database.entities.ScoresEntity
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {

    fun getAllScores(): Flow<List<ScoresEntity>>

    suspend fun insertScores(scoresEntity: ScoresEntity)

}