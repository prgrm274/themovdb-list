package com.man.movies.screen.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.man.data.local.entity.MovieEntity
import com.man.data.repository.RepositoryMovieImpl
import com.man.domain.model.MovieItemsModel
import com.man.movies.R
import com.man.movies.databinding.ActivityDetailBinding
import com.man.movies.extentions.fromUrl
import com.man.movies.screen.dashboard.ui.VMDetail
import com.man.movies.screen.dashboard.ui.ViewModelMovies

class DetailActivity2 : AppCompatActivity() {
    private var b: ActivityDetailBinding? = null
    lateinit var detailMovieItems: MovieItemsModel
    lateinit var movieEntity: MovieEntity

    private lateinit var vmDetail: VMDetail
    private var movieId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        b = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(b!!.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        movieEntity = intent.getSerializableExtra("EXTRA_MOVIE") as MovieEntity
        movieEntity.let {
            title = it.title
            b?.imgPosterDetail?.fromUrl(it.posterPath)
            b?.txtRating?.text = getString(R.string.rating, it.voteAverage.toString())
            b?.txtTitle?.text = it.title
            b?.txtOverview?.text = it.overview
            b?.txtRelease?.text = getString(R.string.release_date, it.releaseDate)
        }

        vmDetail = ViewModelProviders.of(this).get(VMDetail::class.java)
        vmDetail.movieEntity.observe(this, Observer {
            Toast.makeText(this, "movieEntity=> $it selected", Toast.LENGTH_SHORT).show()
        })

        b?.imgPosterDetail?.setOnLongClickListener{
            vmDetail.addMovie(movieEntity)

            Toast.makeText(this, "movieEntity=> ${movieEntity.title} added", Toast.LENGTH_SHORT).show()

            true
        }
    }
}