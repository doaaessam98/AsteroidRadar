package com.example.asteroidradar.ui.service

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.asteroidradar.data.repository.IRepository
import com.example.asteroidradar.data.source.remote.api.getToday

import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
open class RefreshDataWorker @AssistedInject constructor(
    @Assisted val context: Context,
    @Assisted params: WorkerParameters,
    val repo: IRepository
) : CoroutineWorker(context, params) {

    @SuppressLint("RestrictedApi")
    override suspend fun doWork(): Result {

        return try {
            repo.refreshAsteroidsFromApiToDatabase(getToday())
            Result.Success()
        } catch (ex: Exception) {
            Result.failure()
        }
    }
}