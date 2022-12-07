package com.example.asteroidradar.di


import com.example.asteroidradar.data.repository.IRepository
import com.example.asteroidradar.data.repository.Repository
import com.example.asteroidradar.data.source.local.ILocalDataSource
import com.example.asteroidradar.data.source.local.LocalDataSource
import com.example.asteroidradar.data.source.remote.IRemoteDataSource
import com.example.asteroidradar.data.source.remote.RemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface AppModule {

    @Binds
    fun provideLocalDataSource(localDataSource: LocalDataSource): ILocalDataSource

    @Binds
    fun provideRemoteDataSource(remoteDataSource: RemoteDataSource): IRemoteDataSource

    @Binds
    fun provideRepository(repository: Repository): IRepository

}