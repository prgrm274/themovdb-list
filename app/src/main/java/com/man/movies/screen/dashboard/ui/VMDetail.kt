package com.man.movies.screen.dashboard.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.man.data.local.entity.MovieEntity
import com.man.data.repository.RepositoryMovieDataSource
import com.man.data.repository.RepositoryMovieImpl
import com.man.domain.model.MovieItemsModel

class VMDetail(application: Application) : AndroidViewModel(application) {

    val movieName: String? = null
    val movieEntity = MutableLiveData<MovieEntity>()

    val movieId = MutableLiveData<Int>()

    fun addMovie(m: MovieEntity) {

    }


}