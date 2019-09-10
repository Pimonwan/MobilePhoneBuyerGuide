package com.myapplication.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MobileImageResponse (
    @SerializedName("id")
    val id: Int,
    @SerializedName("mobile_id")
    val mobile_id: Int,
    @SerializedName("url")
    val url: String
): Parcelable
