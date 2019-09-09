package com.myapplication.presenter.viewInterface

import com.myapplication.data.model.MobileDetailResponse

interface MobileListView {
    fun showMobileList(list : List<MobileDetailResponse>)
}