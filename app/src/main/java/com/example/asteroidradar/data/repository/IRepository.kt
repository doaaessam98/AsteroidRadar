package com.example.asteroidradar.data.repository

import com.example.asteroidradar.models.Asteroid
import com.example.asteroidradar.models.PictureOfDay
import com.example.asteroidradar.utils.FilterAsteroids
import com.example.asteroidradar.utils.Result
import kotlinx.coroutines.flow.Flow

interface IRepository {

     suspend fun refreshAsteroidsFromApiToDatabase(startDate: String)
    suspend fun getFilteredAsteroid(filter: FilterAsteroids):Result<Flow<List<Asteroid>>>
    suspend fun getPictureOfDay(): Result<PictureOfDay?>
}