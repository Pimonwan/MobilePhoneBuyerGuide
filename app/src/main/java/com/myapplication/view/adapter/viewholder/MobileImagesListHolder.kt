package com.myapplication.view.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myapplication.data.model.MobileImageResponse
import kotlinx.android.synthetic.main.mobile_image_item_list.view.*

class MobileImagesListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var mobileImage = itemView.mobile_image

    fun bind(item: MobileImageResponse) {
        Glide.with(itemView.context).load(item.url).into(mobileImage)
    }
}