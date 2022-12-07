package com.example.asteroidradar.data.source.remote


import android.content.ContentValues.TAG
import android.util.Log
import com.example.asteroidradar.data.source.remote.api.AsteroidService
import com.example.asteroidradar.data.source.remote.api.parseAsteroidsJsonResponse
import com.example.asteroidradar.models.Asteroid
import com.example.asteroidradar.models.PictureOfDay
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody

import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject


class RemoteDataSource @Inject constructor(private val apiService: AsteroidService):
    IRemoteDataSource {



    override  fun getAsteroids(startData:String): Deferred<String> {
       return apiService.getAsteroids(startData)
    }

    override suspend fun getPictureOfDay(): Response<PictureOfDay> = apiService.getPictureOfDay()
}