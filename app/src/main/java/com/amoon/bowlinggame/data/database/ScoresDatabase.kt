package com.amoon.bowlinggame.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.amoon.bowlinggame.data.database.entities.ScoresEntity


@Database(entities = [ScoresEntity::class], version = 1, exportSchema = false)
abstract class ScoreDatabase : RoomDatabase() {

    abstract fun scoresDao():  ScoresDao
}