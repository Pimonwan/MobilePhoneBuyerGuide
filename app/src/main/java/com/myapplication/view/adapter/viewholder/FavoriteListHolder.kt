package com.myapplication.view.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myapplication.presenter.displaymodel.MobileDetail
import kotlinx.android.synthetic.main.favorite_item_list.view.*

class FavoriteListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var brandText = itemView.brand_text
    private var descriptionText = itemView.description_text
    private var priceText = itemView.price_text
    private var rateText = itemView.rate_text
    private var mobileImage = itemView.mobile_image

    fun bind(item: MobileDetail) {
        brandText.text = item.name
        descriptionText.text = item.description
        priceText.text = item.price
        rateText.text = item.rating
        Glide.with(itemView.context).load(item.thumbImageURL).into(mobileImage)
    }
}