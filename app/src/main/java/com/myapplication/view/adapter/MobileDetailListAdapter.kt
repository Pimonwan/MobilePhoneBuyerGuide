package com.myapplication.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.R
import com.myapplication.presenter.displaymodel.MobileDetail
import com.myapplication.view.adapter.viewholder.MobileDetailListHolder

class MobileDetailListAdapter(private val listener: ItemListClick) : RecyclerView.Adapter<MobileDetailListHolder>() {

    private var mDataArray: List<MobileDetail> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, index: Int): MobileDetailListHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.mobile_item_list, parent, false)
        return MobileDetailListHolder(layout)
    }

    override fun getItemCount(): Int {
        return mDataArray.size
    }

    override fun onBindViewHolder(holder: MobileDetailListHolder, index: Int) {
        val item = mDataArray[index]
        holder.bind(item, listener)
    }

    fun addDataArray(data : List<MobileDetail>){
        mDataArray = data
        this.notifyDataSetChanged()
    }

    interface ItemListClick {
        fun navigateToMobileDetailActivity(detail: MobileDetail)
    }
}
