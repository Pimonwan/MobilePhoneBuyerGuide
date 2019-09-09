package com.myapplication.presenter

import android.util.Log
import com.myapplication.domain.model.MobileDetail
import com.myapplication.domain.presenterInterface.MobileDetailPresenter
import com.myapplication.domain.usecase.MobileDetailUsecase
import com.myapplication.presenter.viewInterface.MobileListView

class TabMobileListPresenter : MobileDetailPresenter {

    private lateinit var view : MobileListView
    private var usecase = MobileDetailUsecase()

    init {
        usecase.initial(this)
    }

    fun setView(view : MobileListView) {
        this.view = view
    }

    fun getMobileDatailList() {
        Log.i("iiii", "presenter")
        usecase.callbackResponse()
    }

    override fun setDataToRecycleView(list: List<MobileDetail>) {
       Log.i("setDataToRecycleView", list.toString())
        view.showMobileList(list)
    }
}