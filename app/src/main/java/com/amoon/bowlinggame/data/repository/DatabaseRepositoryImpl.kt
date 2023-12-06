package com.amoon.bowlinggame.data.repository

import com.amoon.bowlinggame.data.database.ScoresDao
import com.amoon.bowlinggame.data.database.entities.ScoresEntity
import com.amoon.bowlinggame.domain.repository.DatabaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(
    private val dao: ScoresDao
) : DatabaseRepository{

    override fun getAllScores(): Flow<List<ScoresEntity>> = dao.getAllUserScores()

    override suspend fun insertScores(scoresEntity: ScoresEntity) = dao.insertScore(scoresEntity)

}