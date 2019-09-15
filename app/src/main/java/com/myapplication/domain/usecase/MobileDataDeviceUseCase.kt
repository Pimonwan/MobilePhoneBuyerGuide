package com.myapplication.domain.usecase

import android.content.Context
import com.myapplication.data.local.LocalData

class MobileDataDeviceUseCase(context: Context) {

    private val localData = LocalData(context)

    fun getSortOption() : String{
        return localData.getSortOption()
    }

    fun setSortOption(value: String) {
        localData.setSortOption(value)
    }

    fun getFavoriteList() : MutableSet<String> {
        return localData.getFavoriteList()
    }

    fun setFavoriteList(list: MutableSet<String>) {
        localData.setFavoriteList(list)
    }
}