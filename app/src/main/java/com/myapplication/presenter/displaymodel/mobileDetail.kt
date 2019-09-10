package com.myapplication.presenter.displaymodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MobileDetail(
    val brand: String,
    val description: String,
    val id: String,
    val name: String,
    val price: String,
    val rating: String,
    val thumbImageURL: String
): Parcelable