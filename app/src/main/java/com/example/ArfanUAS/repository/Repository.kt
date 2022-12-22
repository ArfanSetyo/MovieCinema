package com.example.ArfanUAS.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.ArfanUAS.model.*
import com.example.ArfanUAS.source.ApiResponse
import com.example.ArfanUAS.source.DataSource
import com.example.ArfanUAS.source.LocalDataSource
import com.example.ArfanUAS.source.RemoteDataSource
import com.example.ArfanUAS.util.AppExecutors
import com.example.ArfanUAS.vo.Resource

// penyimpanan ke cache dalam aplikasi
class Repository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
) : DataSource {

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(remoteData, localDataSource, appExecutors).apply {
                    instance = this
                }
            }
    }


    override fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundRes<PagedList<MovieEntity>, List<DataEntitasMovie>>(appExecutors) {

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()


            override fun createCall(): LiveData<ApiResponse<List<DataEntitasMovie>>> =
                remoteDataSource.getMovies()

            override fun saveCallResult(data: List<DataEntitasMovie>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
                    val movieResponse = MovieEntity(
                        id = response.id,
                        title = response.title,
                        imgPoster = response.imgPoster,
                        rating = response.rating,
                        genre = "----",
                        language = response.language,
                        released = response.released,
                        imgBackground = response.imgBackground,
                        firstAir = response.firstAir,
                        overview = response.overview,
                        popularity = response.popularity,
                        isFav = false
                    )
                    movieList.add(movieResponse)
                }
                localDataSource.insertMovie(movieList)
            }

            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val conf = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllDataMovie(), conf).build()
            }
        }.asLiveData()

    }

    override fun getAllTV(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object :
            NetworkBoundRes<PagedList<TvShowEntity>, List<DataEntitasTv>>(appExecutors) {

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()


            override fun createCall(): LiveData<ApiResponse<List<DataEntitasTv>>> =
                remoteDataSource.getTvShows()

            override fun saveCallResult(data: List<DataEntitasTv>) {
                val tvList = ArrayList<TvShowEntity>()
                for (response in data) {
                    val tvResponse = TvShowEntity(
                        id = response.id,
                        title = response.title,
                        imgPoster = response.imgPoster,
                        rating = response.rating,
                        language = response.language,
                        released = response.released,
                        imgBackground = response.imgBackground,
                        firstAir = response.released,
                        genre = "----",
                        overview = response.overview,
                        popularity = response.popularity,
                        isFav = false
                    )
                    tvList.add(tvResponse)
                }
                localDataSource.insertTVShow(tvList)
            }

            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val conf = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllDataTVShow(), conf).build()
            }
        }.asLiveData()
    }

    override fun getOneMovie(movieID: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundRes<MovieEntity, DetailMovie>(appExecutors) {

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data?.overview.isNullOrEmpty() || data?.genre == "----"

            override fun createCall(): LiveData<ApiResponse<DetailMovie>> =
                remoteDataSource.getDetailMovies(movieID)

            override fun saveCallResult(data: DetailMovie) {
                Log.e("genre", data.genre.toString())
                with(data) {
                    var genreTxt = ""
                    if (genre != null) {
                        for (item in genre) {
                            genreTxt = item?.name + ", " + genreTxt
                        }
                    }

                    val dataDetailMovie = MovieEntity(
                        id = id,
                        title = title,
                        imgPoster = imgPoster,
                        rating = rating,
                        language = language,
                        released = released,
                        imgBackground = imgBackground,
                        genre = genreTxt,
                        firstAir = firstAir,
                        overview = overview,
                        popularity = popularity,
                        isFav = false
                    )
                    localDataSource.updateFavMovie(dataDetailMovie, false)
                }
            }

            override fun loadFromDB(): LiveData<MovieEntity> = localDataSource.getMovieById(movieID)
        }.asLiveData()
    }

    override fun getOneTV(tvShowID: Int): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundRes<TvShowEntity, DetailTvShow>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> =
                localDataSource.getTVShowById(tvShowID)

            override fun shouldFetch(data: TvShowEntity?): Boolean =
                data?.overview.isNullOrEmpty() || data?.genre == "----"

            override fun createCall(): LiveData<ApiResponse<DetailTvShow>> =
                remoteDataSource.getDetailTvShow(tvShowID)

            override fun saveCallResult(data: DetailTvShow) {
                Log.e("genre", data.genre.toString())
                with(data) {
                    var genreTxt = ""
                    if (genre != null) {
                        for (item in genre) {
                            genreTxt = item.name + ", " + genreTxt
                        }
                    }
                    val dataDetailTVShow = TvShowEntity(
                        id = id,
                        title = title,
                        imgPoster = imgPoster,
                        rating = rating,
                        language = language,
                        released = released,
                        genre = genreTxt,
                        imgBackground = imgBackground,
                        firstAir = firstAir,
                        overview = overview,
                        popularity = popularity,
                        isFav = false
                    )
                    localDataSource.updateFavTVShow(dataDetailTVShow, false)
                }
            }

        }.asLiveData()
    }

    override fun setMoviesFav(movie: MovieEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.updateFavMovie(movie, state)
        }
    }

    override fun setTVShowsFav(tvShow: TvShowEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.updateFavTVShow(tvShow, state)
        }
    }

    override fun getMoviesFav(context: Context?): LiveData<PagedList<MovieEntity>> {
        val conf = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavMovies(), conf).build()
    }

    override fun getTVShowsFav(context: Context?): LiveData<PagedList<TvShowEntity>> {
        val conf = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavTVShows(), conf).build()
    }
}