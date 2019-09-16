package com.myapplication.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.myapplication.R
import com.myapplication.presenter.TabMobileListPresenter
import com.myapplication.presenter.displaymodel.MobileDetail
import com.myapplication.presenter.viewInterface.MobileListView
import com.myapplication.view.activity.MainActivity
import com.myapplication.view.activity.MobileDetailActivity
import com.myapplication.view.adapter.MobileDetailListAdapter
import com.myapplication.view.viewInterface.ItemListClick
import com.myapplication.view.viewInterface.SetDataFromDevice
import kotlinx.android.synthetic.main.fragment_tab_mobile_list.*
import javax.inject.Inject

class TabMobileListFragment : BaseFragment(), MobileListView {

    @Inject
    lateinit var presenter: TabMobileListPresenter
    private lateinit var adapter: MobileDetailListAdapter
    private var favListener: ItemListClick.OnClickFavoriteButton? = null

    private var listener: SetDataFromDevice? = null

    private val mListenerItemClick: ItemListClick = object : ItemListClick {
        override fun navigateToMobileDetailActivity(detail: MobileDetail) {
            navigateToMobileDetail(detail)
        }
    }
    private val mListenerFavClickFavoriteButton: ItemListClick.OnClickFavoriteButton =
        object : ItemListClick.OnClickFavoriteButton {
            override fun deleteDataFromFavoriteList(detail: MobileDetail) {
                unFavoriteMobileDetailList(detail)
            }
            override fun addDataToFavoriteList(detail: MobileDetail) {
                addMobileDetailToFavoriteList(detail)
            }
        }

    fun navigateToMobileDetail(detail: MobileDetail) {
        val myIntent = Intent(context, MobileDetailActivity::class.java)
        myIntent.putExtra("mobileDetail", detail)
        startActivity(myIntent)
    }

    fun addMobileDetailToFavoriteList(data: MobileDetail) {
        favListener?.addDataToFavoriteList(data)
    }

    fun unFavoriteMobileDetailList(data: MobileDetail) {
        favListener?.deleteDataFromFavoriteList(data)
        adapter.unFavItem(data)
    }

    fun setDataArray(list: List<MobileDetail>) {
        adapter.addDataArray(list)
    }

    fun getMobileDetailList(): List<MobileDetail> {
        return adapter.getMobileDetailList()
    }

    private fun initView() {
        recyclerView?.let { recyclerView ->
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter =
                MobileDetailListAdapter(mListenerItemClick, mListenerFavClickFavoriteButton).also {
                    adapter = it
                }
        }
    }

    private fun getMobileList() {
        presenter.getMobileDatailList()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity is MainActivity) {
            favListener = activity as ItemListClick.OnClickFavoriteButton
            listener = activity as SetDataFromDevice
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab_mobile_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        presenter.setView(this)
        getMobileList()
    }

    override fun showMobileList(list: List<MobileDetail>) {
        adapter.addDataArray(list)
        listener?.setFavoriteListAndSortFromDevice()
    }

    override fun showErrorMessageToast(throwable: Throwable) {
        val message = "something happend \n" + throwable.message.toString()
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}