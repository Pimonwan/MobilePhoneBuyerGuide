package com.myapplication.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.R
import com.myapplication.domain.model.MobileDetail
import com.myapplication.view.adapter.viewholder.MobileDetailListViewHolder

class MobileDetailListAdapter : RecyclerView.Adapter<MobileDetailListViewHolder>() {

    private var mDataArray: List<MobileDetail> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, index: Int): MobileDetailListViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.mobile_item_list, parent, false)
        return MobileDetailListViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return mDataArray.size
    }

    override fun onBindViewHolder(holder: MobileDetailListViewHolder, index: Int) {
        val item = mDataArray[index]
        Log.i("dddd", item.name)
        holder.bind(item)
    }

    fun addDataArray(data : List<MobileDetail>){
        Log.i("dddd", data.toString())
        mDataArray = data
        this.notifyDataSetChanged()
    }
}
