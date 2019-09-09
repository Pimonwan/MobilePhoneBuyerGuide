package com.myapplication.domain.model

data class MobileDetail(
    var brand: String = "",
    var description: String = "",
    var id: Int = 0,
    var name: String = "",
    var price: String = "",
    var rating: Double = 0.0,
    var thumbImageURL: String = ""
)