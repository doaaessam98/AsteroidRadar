package com.example.asteroidradar.data.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.example.asteroidradar.data.source.local.ILocalDataSource
import com.example.asteroidradar.data.source.remote.RemoteDataSource
import com.example.asteroidradar.data.source.remote.api.asDomainModel
import com.example.asteroidradar.data.source.remote.api.getSeventhDay
import com.example.asteroidradar.data.source.remote.api.getToday
import com.example.asteroidradar.data.source.remote.api.parseAsteroidsJsonResponse
import com.example.asteroidradar.models.Asteroid
import com.example.asteroidradar.models.AsteroidEntity
import com.example.asteroidradar.models.PictureOfDay
import com.example.asteroidradar.utils.FilterAsteroids
import com.example.asteroidradar.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.util.ArrayList


import javax.inject.Inject

class Repository  @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: ILocalDataSource

    ): IRepository {


     override suspend fun refreshAsteroidsFromApiToDatabase(startDate: String)  {
     try {
         var asteroidList: ArrayList<Asteroid>
         withContext(Dispatchers.IO) {
             val remoteAsteroid = remoteDataSource.getAsteroids(getToday()).await()
             asteroidList=  parseAsteroidsJsonResponse(JSONObject(remoteAsteroid))
             localDataSource.clearThenInsertAllSteroid(asteroidList.asDomainModel())
         }
     }catch (e:Exception){
         Log.e(TAG, "refreshAsteroidsFromApiToDatabase: $e")
     }
         
     }

    override suspend fun getPictureOfDay(): Result<PictureOfDay?> {
      return try {
          val response =  remoteDataSource.getPictureOfDay()
          Result.Success(data = response.body())
      }catch (e:Exception){
          Result.Error(e.localizedMessage)
      }

    }


    override suspend fun getFilteredAsteroid(filter: FilterAsteroids) : Result<Flow<List<Asteroid>>> {
        return try {
            val asteroid =
              when(filter) {
                is FilterAsteroids.ShowAllSaveAsteroid->{
                    localDataSource.getAllSteroid()
                }
                is FilterAsteroids.ShowToDayAsteroid->{

                   localDataSource.getToDaySteroid(getToday())
                }
                is FilterAsteroids.ShowWeekAsteroid->{

                   localDataSource.getAllWeekSteroid(getToday(), getSeventhDay())
                }
        }
            Result.Success(asteroid)
        }catch (e:Exception){
          Result.Error(e.localizedMessage)
        }


    }
}

