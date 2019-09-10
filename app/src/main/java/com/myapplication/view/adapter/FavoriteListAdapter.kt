package com.myapplication.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.R
import com.myapplication.presenter.displaymodel.MobileDetail
import com.myapplication.view.adapter.viewholder.FavoriteListHolder

class FavoriteListAdapter  : RecyclerView.Adapter<FavoriteListHolder>() {

    private var mDataArray: ArrayList<MobileDetail> = arrayListOf()


    fun addItemToData(data : MobileDetail) {
        mDataArray.add(data)
        this.notifyDataSetChanged()
    }

    fun removeItemFromData(data: MobileDetail) {
        mDataArray.remove(data)
        this.notifyDataSetChanged()
    }

    fun removeItemFromIndex(viewHolder: RecyclerView.ViewHolder) {
        mDataArray.removeAt(viewHolder.adapterPosition)
        this.notifyDataSetChanged()
    }

    fun getMobileList() : List<MobileDetail> {
        return this.mDataArray
    }

    override fun onCreateViewHolder(parent: ViewGroup, index: Int): FavoriteListHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.favorite_item_list, parent, false)
        return FavoriteListHolder(layout)
    }

    override fun getItemCount(): Int {
        return mDataArray.size
    }

    override fun onBindViewHolder(holder: FavoriteListHolder, index: Int) {
        val item= mDataArray[index]
        holder.bind(item)
    }
}
