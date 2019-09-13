package com.myapplication.presenter.displaymodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MobileImageUrl(
    val id: Int,
    val mobile_id: Int,
    val url: String
) : Parcelable