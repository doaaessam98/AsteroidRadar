package com.example.asteroidradar.models

import com.squareup.moshi.Json

data class PictureOfDay(
 @Json(name ="media_type") val media_type: String,
 val title: String,
 val url: String
)

