package com.myapplication.view.viewInterface

import com.myapplication.presenter.displaymodel.MobileDetail

interface ItemListClick {
    fun navigateToMobileDetailActivity(detail: MobileDetail)

    interface OnClickFavoriteButton {
        fun manageDataToFavoriteList(detail: MobileDetail)
        fun deleteDataFromFavoriteList(detail: MobileDetail)
        fun addFavoriteDataToDevice(id: String)
        fun removeFavoriteFromDevice(id: String)
    }
}