package com.myapplication.presenter

import android.annotation.SuppressLint
import com.myapplication.data.model.MobileDetailResponse
import com.myapplication.domain.usecase.MobileDetailUsecase
import com.myapplication.presenter.mapper.MobileDetailDisplayMapper
import com.myapplication.presenter.viewInterface.MobileListView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TabMobileListPresenter @Inject constructor(private var mobileDetailUsecase: MobileDetailUsecase) {

    private lateinit var view: MobileListView
    private val mapper = MobileDetailDisplayMapper()
    private var mobileObservable = mobileDetailUsecase.callbackMobileDetailResponse()

    init {
        startObservation()
    }

    fun setView(view: MobileListView) {
        this.view = view
    }

    fun getMobileDatailList() {
        mobileDetailUsecase.callbackMobileDetailResponse()
    }

    @SuppressLint("CheckResult")
    private fun startObservation() {
        mobileObservable.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { list -> onSuccessGettingData(list) },
                { throwable -> onFailGettingData(throwable) }
            )
    }

    private fun onSuccessGettingData(list: List<MobileDetailResponse>) {
        val displayList = mapper.mapperToDisplayModel(list)
        view.showMobileList(displayList)
    }

    private fun onFailGettingData(throwable: Throwable) {
        view.showErrorMessageToast(throwable)
    }
}