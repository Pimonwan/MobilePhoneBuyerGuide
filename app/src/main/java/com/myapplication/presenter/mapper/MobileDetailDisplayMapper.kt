package com.myapplication.presenter.mapper

import com.myapplication.data.model.MobileDetailResponse
import com.myapplication.presenter.displaymodel.MobileDetail

class MobileDetailDisplayMapper {

    fun mapperToDisplayModel(list: List<MobileDetailResponse>): List<MobileDetail> {
        val mobileDetailDisplay = arrayListOf<MobileDetail>()
        return list.mapTo(mobileDetailDisplay){
            transformResponseToDisplay(it)
        }
    }

    fun transformResponseToDisplay(item: MobileDetailResponse): MobileDetail{
        return MobileDetail(
            item.brand,
            item.description,
            item.id.toString(),
            item.name,
            "Price: \$${item.price}",
            "Rating: ${item.rating}",
            item.thumbImageURL)
    }
}