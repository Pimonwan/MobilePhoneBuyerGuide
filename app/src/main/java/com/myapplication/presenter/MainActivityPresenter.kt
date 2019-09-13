package com.myapplication.presenter

import com.myapplication.DataString
import com.myapplication.presenter.displaymodel.MobileDetail
import com.myapplication.presenter.viewInterface.MainActivityView
import javax.inject.Inject

class MainActivityPresenter @Inject constructor() {

    private lateinit var view: MainActivityView

    fun setView(view: MainActivityView) {
        this.view = view
    }

    fun sortMobileDetailList(option: String, data: List<MobileDetail>) {
        val sortedList = sortMobileList(option, data)
        view.showMobileDetailAfterSort(sortedList)
    }

    fun sortFavoriteList(option: String, data: List<MobileDetail>) {
        if (data.isNotEmpty()) {
            val sortedList = sortMobileList(option, data)
            view.showFavoriteListAfterSort(sortedList)
        }
    }

    private fun sortMobileList(option: String, data: List<MobileDetail>): List<MobileDetail> {
        val list: List<MobileDetail>
        list = when (option) {
            DataString.optionPriceLowToHigh -> {
                data.sortedBy { T -> T.price }
            }
            DataString.optionRateHighToLow -> {
                val arrayPriceLowToHigh = data.sortedBy { T -> T.price }
                arrayPriceLowToHigh.reversed()
            }
            else -> {
                val arrayRateLowToHigh = data.sortedBy { T -> T.rating }
                arrayRateLowToHigh.reversed()
            }
        }
        return list
    }
}