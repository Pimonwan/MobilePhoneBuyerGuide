package com.myapplication.presenter.viewInterface

import com.myapplication.presenter.displaymodel.MobileDetail

interface MobileListView {
    fun showMobileList(list: List<MobileDetail>)
    fun showErrorMessageToast(throwable: Throwable)
}