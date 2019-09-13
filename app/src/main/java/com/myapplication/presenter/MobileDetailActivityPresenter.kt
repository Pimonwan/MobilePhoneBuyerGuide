package com.myapplication.presenter

import android.annotation.SuppressLint
import com.myapplication.data.model.MobileImageResponse
import com.myapplication.domain.usecase.MobileImageUsecase
import com.myapplication.presenter.mapper.MobileImageUrlMapper
import com.myapplication.presenter.viewInterface.MobileDetailView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MobileDetailActivityPresenter @Inject constructor(private var mobileImageUsecase: MobileImageUsecase) {

    private lateinit var view: MobileDetailView
    private val mapper = MobileImageUrlMapper()
    private lateinit var mobileImageObservable: Observable<List<MobileImageResponse>>

    fun setView(view: MobileDetailView) {
        this.view = view
    }

    fun getMobileImageById(id: String) {
        mobileImageObservable = mobileImageUsecase.callbackMobileImageResponse(id)
        startObservation()
    }

    @SuppressLint("CheckResult")
    fun startObservation() {
        mobileImageObservable.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { list -> onSuccessGettingData(list) },
                { throwable -> onFailGettingData(throwable) }
            )
    }

    private fun onSuccessGettingData(list: List<MobileImageResponse>) {
        val displayList = mapper.maaperToDisplayModel(list)
        view.setImagesToRecycleView(displayList)
    }

    private fun onFailGettingData(throwable: Throwable) {
        view.showErrorMessageToast(throwable)
    }
}