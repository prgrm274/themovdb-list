package com.man.movies.screen.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.man.domain.model.MovieItemsModel
import com.man.movies.R
import com.man.movies.databinding.MovieListItemBinding
import com.man.movies.extentions.fromUrl
import com.man.movies.extentions.inflate

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    var listMovie = arrayListOf<MovieItemsModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {

        return SearchViewHolder(parent.inflate(R.layout.movie_list_item))
    }

    override fun getItemCount(): Int = listMovie.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    fun setNewData(list: List<MovieItemsModel>){
        listMovie.clear()
        listMovie.addAll(list)
        notifyDataSetChanged()
    }

    class SearchViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private var b: MovieListItemBinding? = null

        fun bind(item: MovieItemsModel){
            b = MovieListItemBinding.bind(itemView)
            with(itemView){
                item.let {
                    b?.imgPosterMovie?.fromUrl(item.posterPath)
                    b?.txtTitleMovie?.text = item.title
                    b?.txtRating?.text = "${item.voteAverage}"
                }
            }
        }
    }

}