package com.example.asteroidradar.data.source.remote.api

import com.example.asteroidradar.models.PictureOfDay
import com.example.asteroidradar.utils.Constants
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AsteroidService {
    @GET("neo/rest/v1/feed?")
      fun getAsteroids(
        @Query("start_date") start_date: String,
        @Query("api_key") api_key: String = Constants.API_KEY,

    ): Deferred<String>



    @GET("planetary/apod")
    suspend fun getPictureOfDay(
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): Response<PictureOfDay>
}

