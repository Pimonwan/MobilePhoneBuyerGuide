package com.myapplication.view.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myapplication.R
import com.myapplication.presenter.displaymodel.MobileDetail
import com.myapplication.view.viewInterface.ItemListClick
import kotlinx.android.synthetic.main.mobile_item_list.view.*

class MobileDetailListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var brand = itemView.brand_text
    private var description = itemView.description_text
    private var price = itemView.price_text
    private var rate = itemView.rate_text
    private var mobileImage = itemView.mobile_image
    private var favButt = itemView.favorite_button

    fun bind(
        item: MobileDetail,
        listener: ItemListClick,
        favListener: ItemListClick.OnClickFavoriteButton
    ) {
        brand.text = item.name
        description.text = item.description
        price.text = item.price
        rate.text = item.rating
        Glide.with(itemView.context).load(item.thumbImageURL).into(mobileImage)
        itemView.setOnClickListener {
            listener.navigateToMobileDetailActivity(item)
        }

        if (item.isFavorite) {
            favButt.setBackgroundResource(R.drawable.ic_favorite_black)
        } else {
            favButt.setBackgroundResource(R.drawable.ic_favorite_border_black)
        }

        favButt.setOnClickListener {
            item.isFavorite = !item.isFavorite
            if (!item.isFavorite) {
                favListener.deleteDataFromFavoriteList(item)
                favButt.setBackgroundResource(R.drawable.ic_favorite_border_black)

            } else {
                favListener.addDataToFavoriteList(item)
                favButt.setBackgroundResource(R.drawable.ic_favorite_black)
            }
            item.isFavorite = !item.isFavorite
        }
    }
}
