package com.myapplication.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClass {
    companion object RetroClass {
        private const val BASE_URL = "https://scb-test-mobile.herokuapp.com"
        private fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun getAPIService() : APIService {
            return getRetrofit().create(APIService::class.java)
        }
    }
}