package com.example.asteroidradar.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "asteroid")
data class AsteroidEntity (
    @PrimaryKey
    val id: Long,
    val name: String,
    val closeApproachDate: String,
    val absoluteMagnitudeH: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardousAsteroid: Boolean,
    val isSentryObject: Boolean=false,

    )