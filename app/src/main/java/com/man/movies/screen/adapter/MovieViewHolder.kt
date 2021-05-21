package com.man.movies.screen.adapter

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.man.data.local.entity.MovieEntity
import com.man.domain.model.MovieItemsModel
import com.man.movies.R
import com.man.movies.databinding.MovieListItemBinding
import com.man.movies.extentions.fromUrl
import com.man.movies.extentions.inflate
import com.man.movies.screen.detail.DetailActivity2

class MovieViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    fun bindTo(item : MovieItemsModel?){
        movieViewHolderB = MovieListItemBinding.bind(itemView)
        with(movieViewHolderB?.root){
            item?.let {
                movieViewHolderB?.imgPosterMovie?.fromUrl(item.posterPath)
                movieViewHolderB?.txtTitleMovie?.text = item.title
                movieViewHolderB?.txtRating?.text = "${item.voteAverage}"
            }

            movieViewHolderB?.movieCard?.setOnClickListener {
                val movieEntity = MovieEntity(
                    posterPath = item!!.posterPath,
                    title = item.title,
                    voteAverage = item.voteAverage,
                    id = item.id,
                    adult = item.adult,
                    genreIds = item.genreIds,
                    originalLanguage = item.originalLanguage,
                    originalTitle = item.originalTitle,
                    overview = item.overview,
                    popularity = item.popularity,
                    releaseDate = item.releaseDate,
                    video = item.video,
                    voteCount = item.voteCount
                )

                val intent = Intent(this?.context, DetailActivity2::class.java)
                intent.putExtra("EXTRA_MOVIE", movieEntity)
                this?.context?.startActivity(intent)
            }
        }
    }

    private var movieViewHolderB: MovieListItemBinding? = null

    companion object {
        fun create(parent: ViewGroup)
        : MovieViewHolder = MovieViewHolder(parent
            .inflate(R.layout.movie_list_item)
        )
    }
}