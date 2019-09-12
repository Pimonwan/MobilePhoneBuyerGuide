package com.myapplication.presenter.viewInterface

import com.myapplication.data.model.MobileImageResponse

interface MobileDetailView {
    fun setImagesToRecycleView(mobileImages: List<MobileImageResponse>)
    fun showErrorMessageToast(throwable: Throwable)
}