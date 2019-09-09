package com.myapplication.domain.usecase

import com.myapplication.data.domainInterface.MobileDetailDomain
import com.myapplication.data.model.MobileDetailResponse
import com.myapplication.data.repositry.MobileDetailRepositry
import com.myapplication.domain.mapper.MobileDetailMapper
import com.myapplication.domain.presenterInterface.MobileDetailPresenter

class MobileDetailUsecase :  MobileDetailDomain {

    lateinit var presenter : MobileDetailPresenter
    val mobileDetailRepositry = MobileDetailRepositry()
    val mapper = MobileDetailMapper()

    init {
        mobileDetailRepositry.initial(this)
    }

    fun initial(presenter : MobileDetailPresenter) {
        this.presenter = presenter
    }
    fun callbackResponse() {
        mobileDetailRepositry.getMobileDetail()
    }

    override fun mapMobileDetailData(list: List<MobileDetailResponse>){
        val mobileList = mapper.mapMobileDetail(list)
        presenter.setDataToRecycleView(mobileList)
    }

}