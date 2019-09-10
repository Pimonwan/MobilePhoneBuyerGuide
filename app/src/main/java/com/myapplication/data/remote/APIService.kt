package com.myapplication.data.remote

import com.myapplication.data.model.MobileDetailResponse
import com.myapplication.data.model.MobileImageResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {
    @GET("/api/mobiles/")
    fun getMobileDetail2() : Observable<List<MobileDetailResponse>>

    @GET("/api/mobiles/{id}/images/")
    fun getMobileImage(@Path("id") id : String): Observable<List<MobileImageResponse>>

}