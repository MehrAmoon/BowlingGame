package com.amoon.bowlinggame.presentation.di

import android.content.Context
import androidx.room.Room
import com.amoon.bowlinggame.BuildConfig
import com.amoon.bowlinggame.data.database.ScoreDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {


    @Singleton
    @Provides
    fun provideScoreDatabase(@ApplicationContext context: Context): ScoreDatabase =
        Room.databaseBuilder(
            context,
            ScoreDatabase::class.java,
            BuildConfig.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
}