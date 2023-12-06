package com.amoon.bowlinggame.data.database

import androidx.room.*
import com.amoon.bowlinggame.data.database.entities.ScoresEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ScoresDao {

    @Query("SELECT * FROM scores_tbl ORDER BY id DESC")
    fun getAllUserScores(): Flow<List<ScoresEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScore(score: ScoresEntity)

}