package com.myapplication.domain.presenterInterface

import com.myapplication.domain.model.MobileDetail

interface MobileDetailPresenter {
    fun setDataToRecycleView(list : List<MobileDetail>)
}