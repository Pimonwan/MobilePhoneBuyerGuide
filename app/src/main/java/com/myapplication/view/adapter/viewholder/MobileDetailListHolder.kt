package com.myapplication.view.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myapplication.presenter.displaymodel.MobileDetail
import com.myapplication.view.adapter.MobileDetailListAdapter
import kotlinx.android.synthetic.main.mobile_item_list.view.*

class MobileDetailListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var brand = itemView.brand_text
    private var description = itemView.description_text
    private var price = itemView.price_text
    private var rate = itemView.rate_text
    private var mobileImage = itemView.mobile_image

    fun bind(item: MobileDetail, listener: MobileDetailListAdapter.ItemListClick) {
        brand.text = item.name
        description.text = item.description
        price.text = item.price
        rate.text = item.rating
        itemView.setOnClickListener {
            listener.navigateToMobileDetailActivity(item)
        }

        Glide.with(itemView.context).load(item.thumbImageURL).into(mobileImage)
    }
}
