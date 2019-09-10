package com.myapplication.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.R
import com.myapplication.data.model.MobileImageResponse
import com.myapplication.view.adapter.viewholder.MobileImagesListHolder

class MobileImagesListAdapter : RecyclerView.Adapter<MobileImagesListHolder>() {

    private var mDataArray: List<MobileImageResponse> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, index: Int): MobileImagesListHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.mobile_image_item_list, parent, false)
        return MobileImagesListHolder(layout)
    }

    override fun getItemCount(): Int {
        return mDataArray.size
    }

    override fun onBindViewHolder(holder: MobileImagesListHolder, index: Int) {
        val item = mDataArray[index]
        holder.bind(item)
    }

    fun addDataArray(data : List<MobileImageResponse>){
        mDataArray = data
        this.notifyDataSetChanged()
    }
}