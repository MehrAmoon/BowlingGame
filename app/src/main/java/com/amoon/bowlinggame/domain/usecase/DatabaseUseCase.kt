package com.amoon.bowlinggame.domain.usecase

import com.amoon.bowlinggame.data.database.entities.ScoresEntity
import com.amoon.bowlinggame.domain.repository.DatabaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

open class DatabaseUseCase @Inject constructor(
    private val databaseRepository: DatabaseRepository
) {

    fun getAllScores(): Flow<List<ScoresEntity>>{
        return databaseRepository.getAllScores()
    }

    suspend fun insertScores(scoresEntity: ScoresEntity){
        return databaseRepository.insertScores(scoresEntity)
    }

}