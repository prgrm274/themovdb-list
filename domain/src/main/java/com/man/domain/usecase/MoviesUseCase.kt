package com.man.domain.usecase

import com.man.domain.model.MovieItemsModel
import com.man.domain.model.MoviesResponseModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

interface MoviesUseCase {

    fun getMoviePopular(
        page : Int
    ): Observable<MoviesResponseModel>

    fun getMovieUpComing(
        page : Int
    ): Observable<MoviesResponseModel>

    fun getMovieNowPlaying(
        page : Int
    ): Observable<MoviesResponseModel>

    fun saveMovie(entity : MovieItemsModel) : Completable

    fun getMovieFavorite() : Flowable<List<MovieItemsModel>>

}