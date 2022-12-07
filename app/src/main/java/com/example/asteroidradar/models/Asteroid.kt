package com.example.asteroidradar.models


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
data class Asteroid(
    val id: Long,
    val name: String,
    val closeApproachDate: String,
    val absoluteMagnitudeH: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardousAsteroid: Boolean,
    val isSentryObject: Boolean=false,

) : Parcelable