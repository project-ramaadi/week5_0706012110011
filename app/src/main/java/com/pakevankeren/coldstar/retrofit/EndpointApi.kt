package com.pakevankeren.coldstar.retrofit

import com.pakevankeren.coldstar.model.MovieDetail
import com.pakevankeren.coldstar.model.NowPlaying
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
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
        @Query("page") page: Int,
        @Query("region") region: String
    ): Response<NowPlaying>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String,
    ): Response<MovieDetail>

}