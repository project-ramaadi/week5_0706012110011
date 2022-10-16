package com.pakevankeren.coldstar.repository

import com.pakevankeren.coldstar.config.RetrofitConfig
import com.pakevankeren.coldstar.retrofit.EndpointApi
import javax.inject.Inject

class NowPlayingRepository @Inject constructor(private val api: EndpointApi) {

    suspend fun getNowPlayingData(
        language: String,
        page: Int,
        region: String
    ) = api.getNowPlaying(
        RetrofitConfig.API_KEY,
        language,
        page,
        region
    )
}