package com.example.asteroidradar.utils

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.asteroidradar.R
import com.example.asteroidradar.models.PictureOfDay
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso



@BindingAdapter("statusIcon")
fun bindAsteroidStatusImage(imageView: ImageView, isHazardous: Boolean) {
    val context = imageView.context
    if (isHazardous) {
        Log.e(TAG, "bindAsteroidStatusImage: ", )
        imageView.setImageResource(R.drawable.ic_status_potentially_hazardous)
        imageView.contentDescription =
            context.getString(R.string.potentially_hazardous_asteroid_image)
    } else {
        imageView.setImageResource(R.drawable.ic_status_normal)
        imageView.contentDescription = context.getString(R.string.not_hazardous_asteroid_image)
    }
}

@BindingAdapter("asteroidStatusImage")
fun bindDetailsStatusImage(imageView: ImageView, isHazardous: Boolean) {
    val context = imageView.context
    if (isHazardous) {
        imageView.setImageResource(R.drawable.asteroid_hazardous)
        imageView.contentDescription =
            context.getString(R.string.potentially_hazardous_asteroid_image)
    } else {
        imageView.setImageResource(R.drawable.asteroid_safe)
        imageView.contentDescription = context.getString(R.string.not_hazardous_asteroid_image)
    }
}

@BindingAdapter("astronomicalUnitText")
fun bindTextViewToAstronomicalUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.astronomical_unit_format), number)
}

@BindingAdapter("kmUnitText")
fun bindTextViewToKmUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_unit_format), number)
}

@BindingAdapter("velocityText")
fun bindTextViewToDisplayVelocity(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_s_unit_format), number)
}

@BindingAdapter("pictureOfDayImage")
fun bindPictureOfDay(imageView: ImageView, pictureOfDay: PictureOfDay?) {
    val context = imageView.context
    if (pictureOfDay != null && pictureOfDay.url.isNotBlank()) {

            if (pictureOfDay.media_type =="image") {
                imageView.contentDescription = pictureOfDay.title
                         Picasso.with(context)
                         .load(pictureOfDay.url)
                         .placeholder(R.drawable.placeholder_picture_of_day)
                         .error(R.drawable.no_image_available)
                         .fit()
                         .into(imageView)

            } else {

                imageNotFound(imageView,context)
             }

    } else {
        imageNotFound(imageView,context)
    }

}

fun imageNotFound(imageView: ImageView, context: Context) {
    imageView.setImageResource(R.drawable.no_image_available)
    imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
    imageView.contentDescription =
        context.getString(R.string.this_is_nasa_s_picture_of_day_showing_nothing_yet)
}