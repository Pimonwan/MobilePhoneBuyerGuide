package com.myapplication.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.R
import com.myapplication.data.model.MobileImageResponse
import com.myapplication.presenter.displaymodel.MobileImageUrl
import com.myapplication.view.adapter.viewholder.MobileImagesListHolder
import javax.inject.Inject

class MobileImagesListAdapter @Inject constructor(): RecyclerView.Adapter<MobileImagesListHolder>() {

    private var mDataArray: List<MobileImageUrl> = listOf()

    fun addDataArray(data: List<MobileImageUrl>) {
        mDataArray = data
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, index: Int): MobileImagesListHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.mobile_image_item_list, parent, false)
        return MobileImagesListHolder(layout)
    }

    override fun getItemCount(): Int {
        return mDataArray.size
    }

    override fun onBindViewHolder(holder: MobileImagesListHolder, index: Int) {
        val item = mDataArray[index]
        holder.bind(item)
    }
}