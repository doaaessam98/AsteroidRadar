package com.example.asteroidradar.data.source.local

import androidx.room.withTransaction
import com.example.asteroidradar.data.source.local.db.AsteroidDao
import com.example.asteroidradar.data.source.local.db.AsteroidDatabase
import com.example.asteroidradar.models.Asteroid
import com.example.asteroidradar.models.AsteroidEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class LocalDataSource @Inject constructor(
   private val asteroidDao: AsteroidDao,
   private val asteroidDatabase: AsteroidDatabase
): ILocalDataSource {

    override suspend fun clearThenInsertAllSteroid(asteroids: List<AsteroidEntity>) {
        asteroidDatabase.withTransaction {
            asteroidDao.clearAsteroids()
            asteroidDao.insertAll(asteroids)
        }

    }

    override suspend fun getAllSteroid(): Flow<List<Asteroid>> {
        return asteroidDao.getAllAsteroids()
    }

    override suspend fun getToDaySteroid(today:String): Flow<List<Asteroid>> {
        return asteroidDao.getAsteroidToday(today)
    }

    override suspend fun getAllWeekSteroid(startDate:String,endDate:String): Flow<List<Asteroid>> {
        return asteroidDao.getLastWeekAsteroid(startDate,endDate)
    }



}