package com.myapplication.presenter

import android.util.Log
import com.myapplication.data.model.MobileDetailResponse
import com.myapplication.domain.usecase.MobileDetailUsecase
import com.myapplication.presenter.viewInterface.MobileListView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TabMobileListPresenter {

    private lateinit var view : MobileListView
    private var usecase = MobileDetailUsecase()
    private var mobileObservable: Observable<List<MobileDetailResponse>>

    init {
        mobileObservable = usecase.callbackMobileDetailResponse()
        mobileObservable.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ list ->  setDataToRecycleView(list) }, { throwable -> Log.i("uuuu",throwable.message) } )
    }

    fun setView(view : MobileListView) {
        this.view = view
    }

    fun getMobileDatailList() {
        usecase.callbackMobileDetailResponse()
    }

    private fun setDataToRecycleView(list: List<MobileDetailResponse>) {
        view.showMobileList(list)
    }
}