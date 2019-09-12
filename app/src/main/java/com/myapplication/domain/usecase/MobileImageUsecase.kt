package com.myapplication.domain.usecase

import com.myapplication.data.model.MobileImageResponse
import com.myapplication.data.repositry.MobileDetailRepositry
import io.reactivex.Observable
import javax.inject.Inject

class MobileImageUsecase @Inject constructor(private var mobileDetailRepositry : MobileDetailRepositry){

    private lateinit var mobileImageObservable: Observable<List<MobileImageResponse>>

    fun callbackMobileImageResponse(id : String) : Observable<List<MobileImageResponse>> {
        mobileImageObservable = mobileDetailRepositry.getMobileImageObservable(id)
        return mobileImageObservable
    }
}
