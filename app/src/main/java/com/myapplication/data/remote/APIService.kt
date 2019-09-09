package com.myapplication.data.remote

import com.myapplication.data.model.MobileDetailResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface APIService {
    @GET("/api/mobiles/")
    fun getMobileDetail2() : Observable<List<MobileDetailResponse>>
}