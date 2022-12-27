package com.kudos.forageapp.di

import android.content.Context
import androidx.room.Room
import com.kudos.forageapp.db.ForageDatabase
import com.kudos.forageapp.db.dao.ForageDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
private object DatabaseModule {

    @Provides
    fun provideForageDao(forageDatabase: ForageDatabase): ForageDao {
        return forageDatabase.forageDao()
    }

    @Provides
    @Singleton
    fun provideForageDatabase(
        @ApplicationContext context: Context,
    ): ForageDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ForageDatabase::class.java,
            "ForageDatabase"
        ).build()
    }

}