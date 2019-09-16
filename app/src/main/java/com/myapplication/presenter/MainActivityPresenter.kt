package com.myapplication.presenter

import android.content.Context
import com.myapplication.DataString
import com.myapplication.domain.usecase.MobileDataDeviceUseCase
import com.myapplication.presenter.displaymodel.MobileDetail
import com.myapplication.presenter.viewInterface.MainActivityView
import javax.inject.Inject

class MainActivityPresenter @Inject constructor() {

    private lateinit var view: MainActivityView
    private lateinit var localUsecase: MobileDataDeviceUseCase

    fun setView(view: MainActivityView, context: Context) {
        this.view = view
        localUsecase = MobileDataDeviceUseCase(context)
    }

    fun getSortOptionFromDevice(): String {
        return localUsecase.getSortOption()
    }

    fun setSortOptionInDevice(option: String) {
        localUsecase.setSortOption(option)
    }

    fun getFavoriteToSetView(mobileList: List<MobileDetail>) {
        val list = getFavoriteListFromDevice()
        val favList = arrayListOf<MobileDetail>()
        for (item in mobileList) {
            if (list.contains(item.id)) {
                item.isFavorite = true
                favList.add(item)
            }
        }
        view.showFavoriteFromDevice(mobileList, favList)
    }

    private fun getFavoriteListFromDevice(): MutableSet<String> {
        return localUsecase.getFavoriteList()
    }

    fun addFavoriteMobileInListInDevice(id: String) {
        val favList = getFavoriteListFromDevice()
        val newFavList = mutableSetOf<String>()
        for (item in favList) {
            newFavList.add(item)
        }
        if (!newFavList.contains(id)) {
            newFavList.add(id)
            localUsecase.setFavoriteList(newFavList)
        }
    }

    fun removeFavoriteMobileFromListInDevice(id: String) {
        val favList = getFavoriteListFromDevice()
        val newFavList = mutableSetOf<String>()
        for (item in favList) {
            newFavList.add(item)
        }
        if (newFavList.contains(id)) {
            newFavList.remove(id)
            localUsecase.setFavoriteList(newFavList)
        }
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
            DataString.optionPriceHighToLow -> {
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