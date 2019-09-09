package com.myapplication.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.R
import com.myapplication.data.model.MobileDetailResponse
import com.myapplication.view.adapter.viewholder.MobileDetailListViewHolder

class MobileDetailListAdapter : RecyclerView.Adapter<MobileDetailListViewHolder>() {

    private var mDataArray: List<MobileDetailResponse> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, index: Int): MobileDetailListViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.mobile_item_list, parent, false)
        return MobileDetailListViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return mDataArray.size
    }

    override fun onBindViewHolder(holder: MobileDetailListViewHolder, index: Int) {
        val item = mDataArray[index]
        holder.bind(item)
    }

    fun addDataArray(data : List<MobileDetailResponse>){
        mDataArray = data
        this.notifyDataSetChanged()
    }
}
