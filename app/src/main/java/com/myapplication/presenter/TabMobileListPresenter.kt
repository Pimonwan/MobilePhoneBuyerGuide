package com.myapplication.presenter

import com.myapplication.data.model.MobileDetailResponse
import com.myapplication.domain.usecase.MobileDetailUsecase
import com.myapplication.presenter.mapper.MobileDetailDisplayMapper
import com.myapplication.presenter.viewInterface.MobileListView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TabMobileListPresenter {

    private lateinit var view : MobileListView
    private var mobileDetailUsecase = MobileDetailUsecase()
    private val mapper = MobileDetailDisplayMapper()
    private var mobileObservable: Observable<List<MobileDetailResponse>>

    init {
        mobileObservable = mobileDetailUsecase.callbackMobileDetailResponse()
        startObservation()
    }

    fun setView(view : MobileListView) {
        this.view = view
    }

    fun getMobileDatailList() {
        mobileDetailUsecase.callbackMobileDetailResponse()
    }

    private fun startObservation() {
        mobileObservable.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { list ->  onSuccessGettingData(list) },
                { throwable ->  onFailGettingData(throwable)}
            )
    }

    private fun onSuccessGettingData(list: List<MobileDetailResponse>) {
        val displayList = mapper.mapperToDisplayModel(list)
        view.showMobileList(displayList)
    }

    private fun onFailGettingData(throwable : Throwable) {
        view.showErrorMessageToast(throwable)
    }
}