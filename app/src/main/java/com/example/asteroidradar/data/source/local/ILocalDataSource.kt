package com.example.asteroidradar.data.source.local

import com.example.asteroidradar.data.source.local.db.AsteroidDatabase
import com.example.asteroidradar.models.Asteroid
import com.example.asteroidradar.models.AsteroidEntity
import kotlinx.coroutines.flow.Flow


interface ILocalDataSource {
     suspend fun clearThenInsertAllSteroid(asteroids: List<AsteroidEntity>)
     suspend fun getAllSteroid(): Flow<List<Asteroid>>
     suspend fun getToDaySteroid(today:String): Flow<List<Asteroid>>
     suspend fun getAllWeekSteroid(startDate:String,endDate:String): Flow<List<Asteroid>>

}