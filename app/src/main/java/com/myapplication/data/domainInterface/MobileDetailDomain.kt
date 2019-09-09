package com.myapplication.data.domainInterface

import com.myapplication.data.model.MobileDetailResponse

interface MobileDetailDomain {
    fun mapMobileDetailData(list : List<MobileDetailResponse>)
}