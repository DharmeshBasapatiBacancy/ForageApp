package com.kudos.forageapp.di

import com.kudos.forageapp.db.dao.ForageDao
import com.kudos.forageapp.repository.ForageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideForageRepository(forageDao: ForageDao): ForageRepository {
        return ForageRepository(forageDao)
    }

}