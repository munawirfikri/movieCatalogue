package com.munawirfikri.bajp_submission3.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.munawirfikri.bajp_submission3.data.source.local.entity.MovieEntity
import com.munawirfikri.bajp_submission3.data.source.MovieRepository
import com.munawirfikri.bajp_submission3.vo.Resource

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> = movieRepository.getAllMovies()
}