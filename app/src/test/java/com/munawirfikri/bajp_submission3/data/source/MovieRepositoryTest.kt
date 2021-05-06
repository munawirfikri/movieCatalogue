package com.munawirfikri.bajp_submission3.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.munawirfikri.bajp_submission3.data.source.local.LocalDataSource
import com.munawirfikri.bajp_submission3.data.source.local.entity.MovieEntity
import com.munawirfikri.bajp_submission3.data.source.local.entity.TvShowEntity
import com.munawirfikri.bajp_submission3.data.source.remote.RemoteDataSource
import com.munawirfikri.bajp_submission3.utils.AppExecutors
import com.munawirfikri.bajp_submission3.utils.DataDummy
import com.munawirfikri.bajp_submission3.utils.PagedListUtil
import com.munawirfikri.bajp_submission3.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val movieRepository = FakeMovieRepository(remote, local, appExecutors)

    private val movieReponses = DataDummy.generateRemoteDummyMovies()
    private val tvShowReponses = DataDummy.generateRemoteDummyTvShow()


    @Test
    fun getAllMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getAllMovies()).thenReturn(dataSourceFactory)
        movieRepository.getAllMovies()
        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getAllMovies()
        assertNotNull(movieEntities.data)
        assertEquals(movieReponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getAllTvShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getAllTvShows()).thenReturn(dataSourceFactory)
        movieRepository.getAllTvShows()
        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShow()))
        verify(local).getAllTvShows()
        assertNotNull(tvShowEntities.data)
        assertEquals(tvShowReponses.size.toLong(), tvShowEntities.data?.size?.toLong())
    }
}