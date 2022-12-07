package com.example.asteroidradar.data.source.remote

import com.example.asteroidradar.models.PictureOfDay
import kotlinx.coroutines.Deferred

import retrofit2.Response

interface IRemoteDataSource {


      fun getAsteroids(startData:String): Deferred<String>

     suspend fun getPictureOfDay(): Response<PictureOfDay>
}



