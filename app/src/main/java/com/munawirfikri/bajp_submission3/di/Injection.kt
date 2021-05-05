package com.munawirfikri.bajp_submission3.di

import android.content.Context
import com.munawirfikri.bajp_submission3.data.source.MovieRepository
import com.munawirfikri.bajp_submission3.data.source.local.LocalDataSource
import com.munawirfikri.bajp_submission3.data.source.local.room.MovieDatabase
import com.munawirfikri.bajp_submission3.data.source.remote.RemoteDataSource
import com.munawirfikri.bajp_submission3.utils.AppExecutors
import com.munawirfikri.bajp_submission3.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): MovieRepository {

        val database = MovieDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()
        return MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}