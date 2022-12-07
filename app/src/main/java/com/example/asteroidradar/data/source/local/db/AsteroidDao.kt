package com.example.asteroidradar.data.source.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.asteroidradar.models.Asteroid
import com.example.asteroidradar.models.AsteroidEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AsteroidDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(asteroids: List<AsteroidEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg asteroids: AsteroidEntity)
    @Query("SELECT * FROM asteroid ORDER BY closeApproachDate ASC")
    fun getAllAsteroids(): Flow<List<Asteroid>>

    @Query("SELECT * FROM asteroid WHERE closeApproachDate = :date")
     fun getAsteroidToday(date: String): Flow<List<Asteroid>>

    @Query("SELECT * FROM asteroid WHERE substr(closeApproachDate,1,length(:startDate)) BETWEEN :startDate AND :end_date ORDER BY closeApproachDate ASC")
     fun getLastWeekAsteroid(startDate: String , end_date: String): Flow<List<Asteroid>>



    @Query("DELETE FROM asteroid")
    suspend fun clearAsteroids()

}