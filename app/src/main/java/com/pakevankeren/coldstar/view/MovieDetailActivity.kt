package com.pakevankeren.coldstar.view

import android.os.Bundle
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.pakevankeren.coldstar.adapter.GenreAdapter
import com.pakevankeren.coldstar.adapter.ProductionCompanyAdapter
import com.pakevankeren.coldstar.adapter.SpokenLanguageAdapter
import com.pakevankeren.coldstar.config.CommonEnum
import com.pakevankeren.coldstar.config.RetrofitConfig
import com.pakevankeren.coldstar.databinding.ActivityMovieDetailBinding
import com.pakevankeren.coldstar.decoration.SpacesItemDecoration
import com.pakevankeren.coldstar.model.Genre
import com.pakevankeren.coldstar.model.ProductionCompany
import com.pakevankeren.coldstar.model.SpokenLanguage
import com.pakevankeren.coldstar.utils.GlideUtils
import com.pakevankeren.coldstar.viewModel.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailBinding
    private var movieId: Int = 0
    private lateinit var viewModel: MovieDetailViewModel

    // adapters
    private lateinit var spokenLanguageAdapter: SpokenLanguageAdapter
    private lateinit var genreAdapter: GenreAdapter
    private lateinit var productionCompanyAdapter: ProductionCompanyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieId = intent.getIntExtra(CommonEnum.MOVIE_ID_PARCEL, 0)
        viewModel = ViewModelProvider(this)[MovieDetailViewModel::class.java]
        detailViewVisible(false)

        val space = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 8f,
            resources.displayMetrics
        ).toInt()

        // adapters
        binding.movieDetailSpokenLanguageRecyclerview.layoutManager = LinearLayoutManager(
            baseContext,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        binding.movieDetailGenreRecyclerView.layoutManager = LinearLayoutManager(
            baseContext,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        binding.movieDetailProductionCompanyRecyclerview.layoutManager = LinearLayoutManager(
            baseContext,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        binding.movieDetailGenreRecyclerView.addItemDecoration(SpacesItemDecoration(space))
        binding.movieDetailSpokenLanguageRecyclerview.addItemDecoration(SpacesItemDecoration(space))
        binding.movieDetailProductionCompanyRecyclerview.addItemDecoration(
            SpacesItemDecoration(
                space
            )
        )

        // end adapters

        viewModel.getMovieDetail(movieId);
        loadObserver()
    }

    private fun detailViewVisible(state: Boolean) {
        binding.movieDetailScrollView.isVisible = state
        binding.movieDetailLoadingConstraint.isVisible = !state
    }

    private fun loadObserver() {

        viewModel.movieDetail.observe(this) { response ->
            detailViewVisible(true)

            if (response.backdrop_path != null) Glide
                .with(applicationContext)
                .load(RetrofitConfig.IMG_BASE_URL + response.backdrop_path)
                .into(binding.movieDetailBackdropImage)

            if (response.poster_path != null) Glide
                .with(applicationContext)
                .load(RetrofitConfig.IMG_BASE_URL + response.poster_path)
                .into(binding.movieDetailPosterImage)


            spokenLanguageAdapter = SpokenLanguageAdapter(response.spoken_languages.ifEmpty {
                listOf(
                    SpokenLanguage("Unknown", "?", "Unknown")
                )
            })
            binding.movieDetailSpokenLanguageRecyclerview.adapter = spokenLanguageAdapter

            genreAdapter = GenreAdapter(response.genres.ifEmpty {
                listOf(
                    Genre(0, "Unknown")
                )
            })

            binding.movieDetailGenreRecyclerView.adapter = genreAdapter

            productionCompanyAdapter =
                ProductionCompanyAdapter(response.production_companies.ifEmpty {
                    listOf(ProductionCompany(0, null, "?", "Unknown"))
                }, baseContext)
            binding.movieDetailProductionCompanyRecyclerview.adapter = productionCompanyAdapter

            binding.movieDetailMovieTitle.text = response.title
            binding.movieDetailSynopsisText.text =
                response.overview.ifEmpty { "No overview yet..." }
        }
    }


}