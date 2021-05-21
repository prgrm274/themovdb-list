package com.man.domain.di

import com.man.domain.repository.MovieDomainRepository
import com.man.domain.usecase.MoviesUseCase
import com.man.domain.model.MoviesUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun providesMovieUseCase(repository: MovieDomainRepository) : MoviesUseCase {
        return MoviesUseCaseImpl(repository)
    }

}