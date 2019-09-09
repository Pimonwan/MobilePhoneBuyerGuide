package com.myapplication.domain.mapper

import com.myapplication.data.model.MobileDetailResponse
import com.myapplication.domain.model.MobileDetail

class MobileDetailMapper {

    private var mobileDetails = ArrayList<MobileDetail>()

    fun mapMobileDetail(mobileList : List<MobileDetailResponse>) : List<MobileDetail>{
        for (item in mobileList){
            val mobileDetail = MobileDetail()
            mobileDetail.brand = item.brand
            mobileDetail.description = item.description
            mobileDetail.id = item.id
            mobileDetail.name = item.name
            mobileDetail.price = "\$${item.price}"
            mobileDetail.rating = item.rating
            mobileDetail.thumbImageURL = item.thumbImageURL
            mobileDetails.add(mobileDetail)
        }
        return mobileDetails
    }
}