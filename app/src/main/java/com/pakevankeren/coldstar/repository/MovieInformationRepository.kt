package com.pakevankeren.coldstar.repository

import com.pakevankeren.coldstar.config.RetrofitConfig
import com.pakevankeren.coldstar.retrofit.EndpointApi
import javax.inject.Inject

class MovieInformationRepository @Inject constructor(private val api: EndpointApi) {

    suspend fun getMovieDetailResult(
        id: Int,
    ) = api.getMovieDetail(
        id,
        RetrofitConfig.API_KEY,
    )
}