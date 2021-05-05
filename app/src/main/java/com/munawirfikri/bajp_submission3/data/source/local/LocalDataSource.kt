package com.munawirfikri.bajp_submission3.data.source.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.munawirfikri.bajp_submission3.data.source.local.entity.MovieEntity
import com.munawirfikri.bajp_submission3.data.source.local.entity.TvShowEntity
import com.munawirfikri.bajp_submission3.data.source.local.room.MovieDao

class LocalDataSource private constructor(private val mMovieDao: MovieDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            INSTANCE?: LocalDataSource(movieDao)
    }

    fun getAllMovies(): LiveData<List<MovieEntity>> = mMovieDao.getMovies()
    fun getAllTvShows(): LiveData<List<TvShowEntity>> = mMovieDao.getTvShows()
    fun getFavoriteMovie(): LiveData<List<MovieEntity>> = mMovieDao.getFavoriteMovie()
    fun getFavoriteTvShow(): LiveData<List<TvShowEntity>> = mMovieDao.getFavoriteTvShow()
    fun getMovieById(movieId: String): LiveData<MovieEntity> = mMovieDao.getMovieById(movieId)
    fun getTvShowById(tvShowId: String): LiveData<TvShowEntity> = mMovieDao.getTvShowById(tvShowId)
    fun insertMovies(movies: List<MovieEntity>) = mMovieDao.insertMovies(movies)
    fun insertTvShows(tvShows: List<TvShowEntity>) = mMovieDao.insertTvShows(tvShows)
    fun setMovieFavorite(movie: MovieEntity, newState: Boolean) {
        movie.favorite = newState
        mMovieDao.updateMovie(movie)
    }
    fun setTvShowFavorite(tvShow: TvShowEntity, newState: Boolean){
        tvShow.favorite = newState
        mMovieDao.updateTvShow(tvShow)
    }
}