package com.example.asteroidradar.ui.asteroids

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.asteroidradar.data.repository.IRepository
import com.example.asteroidradar.data.source.remote.api.getToday
import com.example.asteroidradar.models.Asteroid
import com.example.asteroidradar.models.PictureOfDay
import com.example.asteroidradar.utils.FilterAsteroids
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import  com.example.asteroidradar.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class AsteroidViewModel @Inject constructor(private val repository: IRepository) :ViewModel(){



    private var _asteroids = MutableStateFlow<List<Asteroid>>(listOf())
    val asteroids: StateFlow<List<Asteroid>>
        get() = _asteroids



    private val _pictureOfDay = MutableLiveData<PictureOfDay?>()
    val pictureOfDay:LiveData<PictureOfDay?> = _pictureOfDay

     init{
           viewModelScope.launch {
              repository .refreshAsteroidsFromApiToDatabase(getToday())
           }
         fetchPictureOfDay()
         filterAsteroid(FilterAsteroids.ShowAllSaveAsteroid)
     }




     private fun fetchPictureOfDay(){
         viewModelScope.launch(Dispatchers.IO) {
             repository.getPictureOfDay().let { result->
                 when(result){
                     is Result.Loading->{

                     }
                     is Result.Error->{
                     }
                     is Result.Success->{
                        _pictureOfDay.postValue(result.data)
                     }
                 }
             } } }

    fun filterAsteroid(filterType:FilterAsteroids) {
        viewModelScope.launch {
            repository.getFilteredAsteroid(filterType).let { it ->
                when(it){
                    is Result.Success->{
                            it.data?.collectLatest{asteroids->
                                _asteroids.value= asteroids
                            }
                    }

                    is Result.Error->{

                    }
                    is Result.Loading->{

                    }
                }
            }
        }
    }



}