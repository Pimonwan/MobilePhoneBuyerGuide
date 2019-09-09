package com.myapplication.data.repositry

import com.myapplication.data.model.MobileDetailResponse
import com.myapplication.data.remote.APIService
import com.myapplication.data.remote.RetrofitClass
import io.reactivex.Observable

class MobileDetailRepositry {

    private lateinit var apiService: APIService
    private lateinit var mobileObservable : Observable<List<MobileDetailResponse>>

    fun getMobileObservable() : Observable<List<MobileDetailResponse>>{
        apiService = RetrofitClass.getAPIService()
        mobileObservable = apiService.getMobileDetail2()
        return mobileObservable
    }
}