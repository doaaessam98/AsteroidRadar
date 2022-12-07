package com.example.asteroidradar.utils

sealed class FilterAsteroids{
    object ShowToDayAsteroid :FilterAsteroids()
    object ShowWeekAsteroid :FilterAsteroids()
    object ShowAllSaveAsteroid :FilterAsteroids()
}
