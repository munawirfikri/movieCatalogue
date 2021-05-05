package com.munawirfikri.bajp_submission3.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.munawirfikri.bajp_submission3.data.source.local.LocalDataSource
import com.munawirfikri.bajp_submission3.data.source.local.entity.MovieEntity
import com.munawirfikri.bajp_submission3.data.source.local.entity.TvShowEntity
import com.munawirfikri.bajp_submission3.data.source.remote.ApiResponse
import com.munawirfikri.bajp_submission3.data.source.remote.RemoteDataSource
import com.munawirfikri.bajp_submission3.data.source.remote.response.MovieResponse
import com.munawirfikri.bajp_submission3.data.source.remote.response.TvShowResponse
import com.munawirfikri.bajp_submission3.utils.AppExecutors
import com.munawirfikri.bajp_submission3.vo.Resource

class MovieRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors)
    : MovieDataSource {

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteData, localData, appExecutors).apply {
                    instance = this
                }
            }
    }

    override fun getAllMovies(): LiveData<Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>, List<MovieResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<MovieEntity>> =
                localDataSource.getAllMovies()


            override fun shouldFetch(data: List<MovieEntity>?): Boolean =
                data == null || data.isEmpty()


            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovies()


            override fun saveCallResult(movieResponses: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in movieResponses) {
                    val movie = MovieEntity(
                        response.id,
                        response.title,
                        response.description,
                        response.category,
                        response.releaseDate,
                        response.score,
                        response.poster,
                        response.type,
                        response.favorite
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getAllTvShows(): LiveData<Resource<List<TvShowEntity>>> {
        return object : NetworkBoundResource<List<TvShowEntity>, List<TvShowResponse>>(appExecutors){
            override fun loadFromDB(): LiveData<List<TvShowEntity>> =
                localDataSource.getAllTvShows()

            override fun shouldFetch(data: List<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getAllTvShows()

            override fun saveCallResult(tvShowResponses: List<TvShowResponse>) {
                val tvShowList = ArrayList<TvShowEntity>()
                for (response in tvShowResponses){
                    val movie = TvShowEntity(response.id,
                        response.title,
                        response.description,
                        response.category,
                        response.releaseDate,
                        response.score,
                        response.poster,
                        response.type,
                        response.favorite
                    )
                    tvShowList.add(movie)
                }
                localDataSource.insertTvShows(tvShowList)
            }
        }.asLiveData()
    }

    override fun getFavoriteMovie(): LiveData<List<MovieEntity>> =
        localDataSource.getFavoriteMovie()


    override fun getFavoriteTvShow(): LiveData<List<TvShowEntity>> =
        localDataSource.getFavoriteTvShow()


    override fun getMovieById(movieId: String): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, List<MovieResponse>>(appExecutors){
            override fun loadFromDB(): LiveData<MovieEntity> =
                localDataSource.getMovieById(movieId)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data == null || data.id.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovies()

            override fun saveCallResult(movieResponses: List<MovieResponse>) {
                lateinit var movie: MovieEntity
                for (response in movieResponses){
                    if (response.id == movieId){
                        movie = MovieEntity(response.id,
                            response.title,
                            response.description,
                            response.category,
                            response.releaseDate,
                            response.score,
                            response.poster,
                            response.type,
                            response.favorite
                        )
                    }
                }
            }
        }.asLiveData()
    }

    override fun getTvShowById(id: String): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, List<TvShowResponse>>(appExecutors){
            override fun loadFromDB(): LiveData<TvShowEntity> =
                localDataSource.getTvShowById(id)

            override fun shouldFetch(data: TvShowEntity?): Boolean =
                data == null || data.id.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getAllTvShows()

            override fun saveCallResult(movieResponses: List<TvShowResponse>) {
                lateinit var movie: TvShowEntity
                for (response in movieResponses){
                    if (response.id == id){
                        movie = TvShowEntity(response.id,
                            response.title,
                            response.description,
                            response.category,
                            response.releaseDate,
                            response.score,
                            response.poster,
                            response.type,
                            response.favorite
                        )
                    }
                }
            }
        }.asLiveData()
    }

    override fun setMovieFavorite(movie: MovieEntity, state: Boolean) =
        appExecutors.diskIO().execute() {localDataSource.setMovieFavorite(movie, state)}


    override fun setTvShowFavorite(tvShow: TvShowEntity, state: Boolean) =
        appExecutors.diskIO().execute() {localDataSource.setTvShowFavorite(tvShow, state)}
}