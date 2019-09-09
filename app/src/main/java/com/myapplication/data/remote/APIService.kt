package com.myapplication.data.remote

import com.myapplication.data.model.MobileDetailResponse
import retrofit2.Call
import retrofit2.http.GET

interface APIService {
    @GET("/api/mobiles/")
    fun getMobileDetail() : Call<List<MobileDetailResponse>>
}