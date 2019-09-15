package com.myapplication.presenter.viewInterface

import com.myapplication.presenter.displaymodel.MobileDetail

interface MainActivityView {
    fun showMobileDetailAfterSort(list: List<MobileDetail>)
    fun showFavoriteListAfterSort(list: List<MobileDetail>)
    fun showFavoriteFromDevice(mobileList: List<MobileDetail>, favList: List<MobileDetail>)
}