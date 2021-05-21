package com.man.movies.screen.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.man.movies.R
import com.man.movies.data.NetworkState
import com.man.movies.databinding.MovieListItemBinding
import com.man.movies.databinding.NetworkStateItemBinding
import com.man.movies.extentions.gone
import com.man.movies.extentions.inflate
import com.man.movies.extentions.visible

class NetworkStateViewHolder (view: View) : RecyclerView.ViewHolder(view)  {
    private var b: NetworkStateItemBinding? = null

    fun bind(networkState: NetworkState?) {
        b = NetworkStateItemBinding.bind(itemView)

        with(itemView){
            if (networkState != null && networkState == NetworkState.LOADING) {
                b!!.networkStateBar.visible()
            } else {
                b?.networkStateBar?.gone()
            }

            if (networkState != null && networkState == NetworkState.ERROR) {
                b?.networkTextView?.visible()
                b?.networkTextView?.text = networkState.msg
            } else if (networkState != null && networkState == NetworkState.ENDOFLIST) {
                b?.networkTextView?.visible()
                b?.networkTextView?.text = "No more items"
            } else {
                b?.networkTextView?.gone()
            }
        }

    }

    companion object {
        fun create(parent: ViewGroup): NetworkStateViewHolder =
            NetworkStateViewHolder(parent.inflate(R.layout.network_state_item))
    }
}