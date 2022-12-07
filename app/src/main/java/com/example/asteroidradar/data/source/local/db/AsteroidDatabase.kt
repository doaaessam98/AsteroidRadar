package com.example.asteroidradar.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.asteroidradar.models.Asteroid
import com.example.asteroidradar.models.AsteroidEntity


@Database(
    entities = [AsteroidEntity::class],
    version = 4,
    exportSchema = false
)
 abstract class AsteroidDatabase : RoomDatabase(){

    abstract fun asteroidDao(): AsteroidDao


}