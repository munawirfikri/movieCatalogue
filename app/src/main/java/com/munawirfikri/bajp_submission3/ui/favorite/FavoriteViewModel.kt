package com.munawirfikri.bajp_submission3.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.munawirfikri.bajp_submission3.data.source.MovieRepository
import com.munawirfikri.bajp_submission3.data.source.local.entity.MovieEntity
import com.munawirfikri.bajp_submission3.data.source.local.entity.TvShowEntity

class FavoriteViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getFavoriteMovies(): LiveData<List<MovieEntity>> = movieRepository.getFavoriteMovie()
    fun getFavoriteTvShows(): LiveData<List<TvShowEntity>> = movieRepository.getFavoriteTvShow()
}