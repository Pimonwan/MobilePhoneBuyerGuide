package com.myapplication.presenter.mapper

import com.myapplication.data.model.MobileImageResponse
import com.myapplication.presenter.displaymodel.MobileImageUrl

class MobileImageUrlMapper {
    fun maaperToDisplayModel(list: List<MobileImageResponse>): List<MobileImageUrl> {
        val mobileDetailDisplay = arrayListOf<MobileImageUrl>()
        return list.mapTo(mobileDetailDisplay){
            transformResponseToDisplay(it)
        }
    }

    private fun transformResponseToDisplay(item: MobileImageResponse) : MobileImageUrl {
        var url = item.url
        if (!url.startsWith("http")) {
            url = "http://" + item.url
        }
        return MobileImageUrl(
            item.id,
            item.mobile_id,
            url
        )
    }
}