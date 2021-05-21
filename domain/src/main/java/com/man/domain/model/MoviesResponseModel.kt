package com.man.domain.model

data class MoviesResponseModel(
    val page: Int,
    val totalPages: Int,
    val results: List<MovieItemsModel>,
    val totalResults: Int
)
