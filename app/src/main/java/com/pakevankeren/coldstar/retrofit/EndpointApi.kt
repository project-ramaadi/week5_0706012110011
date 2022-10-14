package com.pakevankeren.coldstar.retrofit

import com.pakevankeren.coldstar.model.NowPlaying
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EndpointApi {

    /**
     * Get now playing API Endpoint
     * Get the currently airing movies. Stored to the model as a NowPlaying model
     * @see NowPlaying - the model
     * */
    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<NowPlaying>

}