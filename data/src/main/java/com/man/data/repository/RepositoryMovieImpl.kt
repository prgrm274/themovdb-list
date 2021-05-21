package com.man.data.repository

import com.man.data.local.entity.MovieEntity
import com.man.data.remote.model.MoviesResponse
import com.man.domain.model.MovieItemsModel
import com.man.domain.model.MoviesResponseModel
import com.man.domain.repository.MovieDomainRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

class RepositoryMovieImpl(
    private val localDataSource: RepositoryMovieDataSource,
    private val remoteDataSource: RepositoryMovieDataSource
) : MovieDomainRepository {

    override fun getMoviePopular(
        page : Int
    ): Observable<MoviesResponseModel> {
        return remoteDataSource.getMoviePopular(page)
            .map { MoviesResponse.transform(it) }
    }

    override fun getMovieUpComing(
        page : Int
    ): Observable<MoviesResponseModel> {
        return remoteDataSource.getMovieUpComing(page)
            .map { MoviesResponse.transform(it)  }
    }

    override fun getMovieNowPlaying(
        page : Int
    ): Observable<MoviesResponseModel> {
        return remoteDataSource.getMovieNowPlaying(page)
            .map { MoviesResponse.transform(it)  }
    }

    override fun saveMovie(entity: MovieItemsModel): Completable =
        localDataSource.saveMovie(MovieEntity.transform(entity))

    override fun getMovieFavorite(): Flowable<List<MovieItemsModel>> =
        localDataSource.getMovieFavorite().map { MovieEntity.transform(it) }


}