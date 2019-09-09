package com.myapplication.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.myapplication.R
import com.myapplication.domain.model.MobileDetail
import com.myapplication.presenter.TabMobileListPresenter
import com.myapplication.presenter.viewInterface.MobileListView
import com.myapplication.view.adapter.MobileDetailListAdapter
import kotlinx.android.synthetic.main.fragment_tab_mobile_list.*

class TabMobileListFragment : Fragment() , MobileListView{

    private lateinit var presenter : TabMobileListPresenter
    private lateinit var adapter: MobileDetailListAdapter

    private fun initView() {
        recyclerView?.let { recyclerView ->
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = MobileDetailListAdapter().also {
                adapter = it
            }
        }
        presenter = TabMobileListPresenter()
    }

    private fun getMobileList() {
        Log.i("iiii", "tabMobileList")
        presenter.getMobileDatailList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_mobile_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        presenter.setView(this)
        getMobileList()
    }

    override fun showMobileList(list: List<MobileDetail>) {
        Log.i("iiii", list.toString())
        adapter.addDataArray(list)
    }
}
