package com.myapplication.data.repositry

import com.myapplication.data.model.MobileDetailResponse
import com.myapplication.data.model.MobileImageResponse
import com.myapplication.data.remote.RetrofitClass
import io.reactivex.Observable
import javax.inject.Inject

class MobileDetailRepositry @Inject constructor(api: RetrofitClass){

    private var apiService = api.getAPIService()

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