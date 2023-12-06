package com.amoon.bowlinggame.presentation.di

import com.amoon.bowlinggame.data.repository.DatabaseRepositoryImpl
import com.amoon.bowlinggame.domain.repository.DatabaseRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindDatabaseRepository(databaseRepositoryImpl: DatabaseRepositoryImpl): DatabaseRepository

}