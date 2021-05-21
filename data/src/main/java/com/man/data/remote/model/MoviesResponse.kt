package com.man.data.remote.model

import com.google.gson.annotations.SerializedName
import com.man.data.remote.model.base.BaseResponse
import com.man.domain.model.MoviesResponseModel

data class MoviesResponse(

	@SerializedName("results")
	val results: List<MovieItems>

) : BaseResponse(){
	companion object {
		fun transform(response: MoviesResponse) : MoviesResponseModel {
			return MoviesResponseModel(
				page = response.page,
				totalPages = response.totalPages,
				totalResults = response.totalResults,
				results = MovieItems.transform(response.results)
			)
		}
	}
}
