package com.munawirfikri.bajp_submission3.data.source

import androidx.lifecycle.LiveData
import com.munawirfikri.bajp_submission3.data.source.local.entity.MovieEntity
import com.munawirfikri.bajp_submission3.data.source.local.entity.TvShowEntity
import com.munawirfikri.bajp_submission3.vo.Resource

interface MovieDataSource {
    fun getAllMovies(): LiveData<Resource<List<MovieEntity>>>
    fun getAllTvShows(): LiveData<Resource<List<TvShowEntity>>>
    fun getFavoriteMovie(): LiveData<List<MovieEntity>>
    fun getFavoriteTvShow(): LiveData<List<TvShowEntity>>
    fun getMovieById(id: String): LiveData<Resource<MovieEntity>>
    fun getTvShowById(id: String): LiveData<Resource<TvShowEntity>>
    fun setMovieFavorite(movie: MovieEntity, state: Boolean)
    fun setTvShowFavorite(tvShow: TvShowEntity, state: Boolean)
}