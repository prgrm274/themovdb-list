package com.man.domain.model

import com.man.domain.repository.MovieDomainRepository
import com.man.domain.usecase.MoviesUseCase
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

class MoviesUseCaseImpl (
    private val repository: MovieDomainRepository
) : MoviesUseCase {

    override fun getMoviePopular(
        page : Int
    ): Observable<MoviesResponseModel> = repository.getMoviePopular(page)

    override fun getMovieUpComing(
        page : Int
    ): Observable<MoviesResponseModel> = repository.getMovieUpComing(page)

    override fun getMovieNowPlaying(
        page : Int
    ): Observable<MoviesResponseModel> = repository.getMovieNowPlaying(page)

    override fun saveMovie(entity: MovieItemsModel): Completable =
        repository.saveMovie(entity)

    override fun getMovieFavorite(): Flowable<List<MovieItemsModel>> =
        repository.getMovieFavorite()


}