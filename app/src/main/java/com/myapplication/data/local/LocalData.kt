package com.myapplication.data.local

import android.content.Context

class LocalData(context: Context){

    private val prefNameKey= "DeviceData"
    private val mode = 0
    private val sortOptionSharedPref = context.getSharedPreferences(prefNameKey, mode)
    private val favoriteSharedPref = context.getSharedPreferences(prefNameKey, mode)

    fun getSortOption() : String{
        var sortOption = sortOptionSharedPref.getString("sortOption", "default")
        if (sortOption.isNullOrBlank()) {
            sortOption = "noChoice"
        }
        return sortOption
    }

    fun setSortOption(sortOption: String) {
        sortOptionSharedPref.edit().putString("sortOption", sortOption).apply()
    }

    fun getFavoriteList(): MutableSet<String> {
        var mobileList = favoriteSharedPref.getStringSet("favoriteList", null)
        if (mobileList.isNullOrEmpty()) {
            mobileList = setOf()
        }
        return mobileList
    }

    fun setFavoriteList(list: MutableSet<String>) {
        favoriteSharedPref.edit().putStringSet("favoriteList", list).apply()
    }
}