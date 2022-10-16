package com.pakevankeren.coldstar.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.pakevankeren.coldstar.R
import com.pakevankeren.coldstar.adapter.NowPlayingAdapter
import com.pakevankeren.coldstar.config.RetrofitConfig
import com.pakevankeren.coldstar.databinding.ActivityNowPlayingBinding
import com.pakevankeren.coldstar.decoration.GridSpacingItemsDecoration
import com.pakevankeren.coldstar.viewModel.NowPlayingViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NowPlayingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNowPlayingBinding
    private lateinit var adapter: NowPlayingAdapter
    private lateinit var viewModel: NowPlayingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNowPlayingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[NowPlayingViewModel::class.java]
        recyclerViewVisible(false)
        loadViewModel()

        binding.nowPlayingRecyclerView.layoutManager = GridLayoutManager(baseContext, 2)
        binding.nowPlayingRecyclerView.addItemDecoration(GridSpacingItemsDecoration(2, 50, true))

        binding.nowPlayingRefreshLayout.setOnRefreshListener {
            binding.nowPlayingRefreshLayout.isRefreshing = true
            val countryList = listOf("ID", "US", "JP", "KR", "SG")
            viewModel.getNowPlaying(region = countryList.random())
        }
    }

    private fun recyclerViewVisible(state: Boolean) {
        binding.nowPlayingRefreshLayout.isVisible = state
        binding.nowPlayingLoader.isVisible = !state
    }

    private fun loadViewModel() {
        viewModel.getNowPlaying(region = "ID")
        viewModel.nowPlaying.observe(this) { response ->
            recyclerViewVisible(true)
            binding.nowPlayingRefreshLayout.isRefreshing = false
            adapter = NowPlayingAdapter(response)
            binding.nowPlayingRecyclerView.adapter = adapter
        }

    }
}