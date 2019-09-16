package com.myapplication.presenter.mapper

import com.myapplication.data.model.MobileDetailResponse
import com.myapplication.presenter.displaymodel.MobileDetail
import kotlin.math.roundToInt

class MobileDetailDisplayMapper {

    fun mapperToDisplayModel(list: List<MobileDetailResponse>): List<MobileDetail> {
        val mobileDetailDisplay = arrayListOf<MobileDetail>()
        return list.mapTo(mobileDetailDisplay){
            transformResponseToDisplay(it)
        }
    }

    private fun transformResponseToDisplay(item: MobileDetailResponse): MobileDetail{
        val price = item.price
        val priceString : String
        if (price % 1 == 0.0) {
            priceString = price.roundToInt().toString()
        } else {
            priceString = price.toString()
        }
        return MobileDetail(
            item.brand,
            item.description,
            item.id.toString(),
            item.name,
            "Price: $$priceString",
            "Rating: ${item.rating}",
            item.thumbImageURL)
    }
}