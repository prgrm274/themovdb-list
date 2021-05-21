package com.man.movies.screen.dashboard.ui.upcoming

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.man.movies.R
import com.man.movies.base.BaseFragment
import com.man.movies.data.NetworkState
import com.man.movies.data.TypeMovie
import com.man.movies.data.source.MoviePagedListRepository
import com.man.movies.databinding.FragmentMovieBinding
import com.man.movies.event.SearchEvent
import com.man.movies.extentions.gone
import com.man.movies.extentions.visible
import com.man.movies.screen.adapter.MovieAdapter
import com.man.movies.screen.adapter.SearchAdapter
import com.man.movies.screen.dashboard.ui.ViewModelMovies
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject

class UpComingFragment : BaseFragment() {

    private lateinit var viewModel: ViewModelMovies
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var searchAdapter: SearchAdapter

    @Inject
    lateinit var repository: MoviePagedListRepository

    override fun getLayoutResource(): Int = R.layout.fragment_movie

    private var b: FragmentMovieBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieAdapter = MovieAdapter()
        searchAdapter = SearchAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        b = FragmentMovieBinding.bind(view)
    }

    override fun onDestroyView() {
        // Consider not storing the binding instance in a field if not needed.
        b = null
        super.onDestroyView()
    }

    override fun initComponent() {
        fragmentComponent.inject(this)
        viewModel = viewModelMovies()
        setupView()
        setupViewSearch()
        showLoadingContainer(true)
    }

    private fun viewModelMovies(): ViewModelMovies {
        return ViewModelProvider(
            this,
            ViewModelMovies.Factory(repository, TypeMovie.UPCOMING)
        )[ViewModelMovies::class.java]
    }

    private fun setupView() {
        viewModel.moviePagedList.observe(viewLifecycleOwner, Observer {
            movieAdapter.submitList(it)
            b?.rvMovie?.layoutManager = GridLayoutManager(activity, 2)
            b?.rvMovie?.setHasFixedSize(true)
            b?.rvMovie?.adapter = movieAdapter
        })

        viewModel.networkState.observe(viewLifecycleOwner, Observer {
            if (viewModel.movieListIsEmpty() && it == NetworkState.LOADING) showLoadingContainer(true)
            else showLoadingContainer(false)

            if (!viewModel.movieListIsEmpty()) {
                movieAdapter.setNetworkState(it)
            }
        })
    }

    private fun setupViewSearch() {
        b?.rvMovieSearch?.layoutManager = GridLayoutManager(activity, 2)
        b?.rvMovieSearch?.setHasFixedSize(true)
        b?.rvMovieSearch?.adapter = searchAdapter
    }

    private fun showLoadingContainer(active: Boolean) {
        if (context == null) return
        layoutView?.let {
            b?.vaMovie?.displayedChild = if (active) 0 else 1
            //it.vaMovie.displayedChild = if (active) 0 else 1
        }
    }

    @Subscribe
    fun onReceiveEventBus(event: SearchEvent) {
        if (event.query.isEmpty()) {
            showMovie()
        } else if(flag) {
            viewModel.getFilteredList(event.query).observe(viewLifecycleOwner, Observer {
                if (it.isEmpty()){
                    Toast.makeText(context, "Not Found Or Try Scroll List Movie", Toast.LENGTH_LONG).show()
                    showMovie()
                }else{
                    searchAdapter.setNewData(it)
                    showSearchResult()
                }
            })
        }
    }

    private fun showMovie(){
        b?.rvMovie?.visible()
        b?.rvMovieSearch?.gone()
    }

    private fun showSearchResult(){
        b?.rvMovie?.gone()
        b?.rvMovieSearch?.visible()
    }
}