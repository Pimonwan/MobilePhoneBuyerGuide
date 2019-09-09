package com.myapplication.domain.usecase

import com.myapplication.data.model.MobileDetailResponse
import com.myapplication.data.repositry.MobileDetailRepositry
import io.reactivex.Observable

class MobileDetailUsecase {

    private lateinit var mobileObservable : Observable<List<MobileDetailResponse>>
    private val mobileDetailRepositry = MobileDetailRepositry()

    fun callbackMobileDetailResponse() : Observable<List<MobileDetailResponse>> {
        mobileObservable = mobileDetailRepositry.getMobileObservable()
        return mobileObservable
    }
}