package com.myapplication.view.fragment


import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.myapplication.R
import com.myapplication.presenter.displaymodel.MobileDetail
import com.myapplication.view.adapter.FavoriteListAdapter
import com.myapplication.view.viewInterface.ItemListClick
import kotlinx.android.synthetic.main.fragment_tab_favorite_mobile.*
import javax.inject.Inject

class TabFavoriteMobileFragment : BaseFragment() {

    @Inject
    lateinit var adapter: FavoriteListAdapter
    private lateinit var animation: AnimationOnSwipeRecyclerList
    private lateinit var deleteIcon: Drawable
    private var unFavListener: ItemListClick.OnClickFavoriteButton? = null

    fun manageFavoriteList(mobileDetail: MobileDetail) {
        if (mobileDetail.isFavorite) {
            adapter.addItemToData(mobileDetail)
        } else {
            adapter.removeItemFromData(mobileDetail)
        }
    }

    fun getFavoriteList(): List<MobileDetail> {
        return adapter.getMobileList()
    }

    fun setDataArray(list: List<MobileDetail>) {
        adapter.setDataArray(list)
    }

    private fun initView() {
        recyclerFavoriteView?.let { recyclerView ->
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = FavoriteListAdapter().also {
                adapter = it
            }
        }
        setAnimationOnSwipeRecyclerList()
    }

    private fun setAnimationOnSwipeRecyclerList() {
        deleteIcon = ContextCompat.getDrawable(context!!, R.drawable.ic_delete_white_24dp)!!
        animation =
            AnimationOnSwipeRecyclerList(deleteIcon, adapter, unFavListener, recyclerFavoriteView)
        animation.setAnimationOnSwipeRecyclerList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab_favorite_mobile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        unFavListener = activity as ItemListClick.OnClickFavoriteButton?
        initView()
    }
}