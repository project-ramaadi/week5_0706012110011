package com.pakevankeren.coldstar.repository

import com.pakevankeren.coldstar.config.RetrofitConfig
import com.pakevankeren.coldstar.retrofit.EndpointApi
import javax.inject.Inject

class NowPlayingRepository @Inject constructor(private val api: EndpointApi) {

    suspend fun getNowPlayingData(language: String = "en-US", page: Int = 1) = api.getNowPlaying(
        RetrofitConfig.API_KEY,
        language,
        page
    )

    suspend fun getMovieDetailResult(id: Int, language: String?) = api.getMovieDetail(
        id,
        RetrofitConfig.API_KEY,
        language ?: "en-US"
    )
}