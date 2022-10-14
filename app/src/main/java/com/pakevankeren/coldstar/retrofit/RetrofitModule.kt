package com.pakevankeren.coldstar.retrofit

import com.pakevankeren.coldstar.config.RetrofitConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Singleton
    @Provides
    fun getRetrofitServiceInstance(retrofit: Retrofit): EndpointApi {
        return retrofit.create(EndpointApi::class.java)
    }

    @Singleton
    @Provides
    fun getRetrofitInstance(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(RetrofitConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}