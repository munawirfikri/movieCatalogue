package com.munawirfikri.bajp_submission3.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.munawirfikri.bajp_submission3.data.source.local.entity.TvShowEntity
import com.munawirfikri.bajp_submission3.data.source.MovieRepository
import com.munawirfikri.bajp_submission3.vo.Resource

class TvShowViewModel(private val movieRepository: MovieRepository): ViewModel() {
    fun getTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> = movieRepository.getAllTvShows()
}