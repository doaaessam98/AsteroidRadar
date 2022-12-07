package com.example.asteroidradar.di

import android.content.Context
import androidx.room.Room
import com.example.asteroidradar.data.source.local.db.AsteroidDao
import com.example.asteroidradar.data.source.local.db.AsteroidDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataBaseModule {



     @Provides
     @Singleton
     fun repoDataBase(@ApplicationContext context: Context): AsteroidDatabase =
         Room.databaseBuilder(context,AsteroidDatabase::class.java,"asteroid_DB").build()

    @Provides
    @Singleton
    fun provideRepoDataBase(db:AsteroidDatabase): AsteroidDao =db.asteroidDao()


}


