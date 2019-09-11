package com.myapplication.view.viewInterface

import com.myapplication.presenter.displaymodel.MobileDetail

interface ItemListClick {
    fun navigateToMobileDetailActivity(detail: MobileDetail)

    interface OnClickFavoriteButton {
        fun addDataToFavoriteList(detail: MobileDetail)
    }

    interface OnClickUnFavoriteButton {
        fun deleteDataFromFavoriteButton(detail: MobileDetail)
    }
}