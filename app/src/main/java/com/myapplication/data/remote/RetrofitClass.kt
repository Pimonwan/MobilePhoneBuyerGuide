package com.myapplication.data.remote

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitClass @Inject constructor() {

    private val baseURL = "https://scb-test-mobile.herokuapp.com"

    fun getAPIService(): APIService {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(APIService::class.java)
    }
}