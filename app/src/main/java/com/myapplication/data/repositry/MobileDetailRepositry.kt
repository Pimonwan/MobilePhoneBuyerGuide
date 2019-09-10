package com.myapplication.data.repositry

import com.myapplication.data.model.MobileDetailResponse
import com.myapplication.data.model.MobileImageResponse
import com.myapplication.data.remote.APIService
import com.myapplication.data.remote.RetrofitClass
import io.reactivex.Observable

class MobileDetailRepositry {

    private var apiService: APIService = RetrofitClass.getAPIService()
    private lateinit var mobileDetailListObservable : Observable<List<MobileDetailResponse>>
    private lateinit var mobileImageObservable: Observable<List<MobileImageResponse>>

    fun getMobileObservable() : Observable<List<MobileDetailResponse>> {
        mobileDetailListObservable = apiService.getMobileDetail2()
        return mobileDetailListObservable
    }

    fun getMobileImageObservable(id : String) : Observable<List<MobileImageResponse>> {
        mobileImageObservable = apiService.getMobileImage(id)
        return mobileImageObservable
    }
}