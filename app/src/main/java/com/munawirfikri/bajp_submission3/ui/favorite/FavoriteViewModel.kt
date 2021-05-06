package com.munawirfikri.bajp_submission3.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.munawirfikri.bajp_submission3.data.source.MovieRepository
import com.munawirfikri.bajp_submission3.data.source.local.entity.MovieEntity
import com.munawirfikri.bajp_submission3.data.source.local.entity.TvShowEntity

class FavoriteViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> = movieRepository.getFavoriteMovie()
    fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>> = movieRepository.getFavoriteTvShow()
    fun setFavoriteMovie(movieEntity: MovieEntity) {
        val newState = !movieEntity.favorite
        movieRepository.setMovieFavorite(movieEntity, newState)
    }
    fun setFavoriteTvShow(tvShowEntity: TvShowEntity) {
        val newState = !tvShowEntity.favorite
        movieRepository.setTvShowFavorite(tvShowEntity, newState)
    }
}