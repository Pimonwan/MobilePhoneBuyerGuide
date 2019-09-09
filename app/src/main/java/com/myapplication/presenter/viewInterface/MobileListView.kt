package com.myapplication.presenter.viewInterface

import com.myapplication.domain.model.MobileDetail

interface MobileListView {
    fun showMobileList(list : List<MobileDetail>)
}