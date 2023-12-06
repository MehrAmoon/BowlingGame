package com.amoon.bowlinggame.presentation.di

import com.amoon.bowlinggame.data.database.ScoreDatabase
import com.amoon.bowlinggame.data.database.ScoresDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideScoresDao(scoreDatabase: ScoreDatabase): ScoresDao =
        scoreDatabase.scoresDao()
}