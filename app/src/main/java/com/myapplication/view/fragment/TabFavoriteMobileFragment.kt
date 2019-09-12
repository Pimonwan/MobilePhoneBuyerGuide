package com.myapplication.view.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.myapplication.R
import com.myapplication.presenter.displaymodel.MobileDetail
import com.myapplication.view.adapter.FavoriteListAdapter
import kotlinx.android.synthetic.main.fragment_tab_favorite_mobile.*

class TabFavoriteMobileFragment  : BaseFragment() {

    private var adapter: FavoriteListAdapter = FavoriteListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab_favorite_mobile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        recyclerFavoriteView?.let { recyclerView ->
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = FavoriteListAdapter().also {
                adapter = it
            }
        }
    }

    fun manageFavoriteList(mobileDetail: MobileDetail) {
        if (mobileDetail.isFavorite){
            adapter.addItemToData(mobileDetail)
        }else{
            adapter.removeItemFromData(mobileDetail)
        }
    }

    fun getFavoriteList() : List<MobileDetail> {
        return adapter.getMobileList()
    }

    fun setDataArray(list: List<MobileDetail>) {
        adapter.setDataArray(list)
    }
}
