package com.myapplication.data.model

import com.google.gson.annotations.SerializedName

data class MobileImageResponse (
    @SerializedName("id")
    val id: Int,
    @SerializedName("mobile_id")
    val mobile_id: Int,
    @SerializedName("url")
    val url: String
)
