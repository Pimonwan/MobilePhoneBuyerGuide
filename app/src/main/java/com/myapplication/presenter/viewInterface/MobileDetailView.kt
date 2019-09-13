package com.myapplication.presenter.viewInterface

import com.myapplication.presenter.displaymodel.MobileImageUrl

interface MobileDetailView {
    fun setImagesToRecycleView(mobileImages: List<MobileImageUrl>)
    fun showErrorMessageToast(throwable: Throwable)
}