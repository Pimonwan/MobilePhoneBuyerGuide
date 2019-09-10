package com.myapplication.domain.usecase

import com.myapplication.data.model.MobileImageResponse
import com.myapplication.data.repositry.MobileDetailRepositry
import io.reactivex.Observable

class MobileImageUsecase {

    private lateinit var mobileImageObservable: Observable<List<MobileImageResponse>>
    private val mobileDetailRepositry = MobileDetailRepositry()

    fun callbackMobileImageResponse(id : String) : Observable<List<MobileImageResponse>> {
        mobileImageObservable = mobileDetailRepositry.getMobileImageObservable(id)
        return mobileImageObservable
    }
}
