package com.myapplication.presenter

import com.myapplication.data.model.MobileImageResponse
import com.myapplication.domain.usecase.MobileImageUsecase
import com.myapplication.presenter.viewInterface.MobileDetailView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MobileDetailActivityPresenter {

    private lateinit var view: MobileDetailView
    private var mobileImageUsecase = MobileImageUsecase()
    private lateinit var mobileImageObservable : Observable<List<MobileImageResponse>>

    fun setView(view: MobileDetailView) {
        this.view = view
    }

    fun getMobileImageById(id : String) {
        mobileImageObservable = mobileImageUsecase.callbackMobileImageResponse(id)
        startObservation()
    }


    fun startObservation() {
        mobileImageObservable.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { list -> onSuccessGettingData(list) },
                { throwable -> onFailGettingData(throwable) }
            )
    }

    private fun onSuccessGettingData(list: List<MobileImageResponse>) {
        view.setImagesToRecycleView(list)
    }

    private fun onFailGettingData(throwable : Throwable) {

    }
}