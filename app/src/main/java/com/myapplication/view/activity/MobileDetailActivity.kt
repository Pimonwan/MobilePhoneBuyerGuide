package com.myapplication.view.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.myapplication.R
import com.myapplication.data.model.MobileImageResponse
import com.myapplication.presenter.MobileDetailActivityPresenter
import com.myapplication.presenter.displaymodel.MobileDetail
import com.myapplication.presenter.viewInterface.MobileDetailView
import com.myapplication.view.adapter.MobileImagesListAdapter
import kotlinx.android.synthetic.main.activity_mobile_detail.*

class MobileDetailActivity : AppCompatActivity(), MobileDetailView {

    private lateinit var mobileDetail : MobileDetail
    private lateinit var presenter : MobileDetailActivityPresenter
    private lateinit var adapter: MobileImagesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mobile_detail)
        initView()
        setView()
    }

    private fun initView() {
        mobileDetail = intent.getParcelableExtra("mobileDetail")
        presenter = MobileDetailActivityPresenter()
        presenter.setView(this)
        presenter.getMobileImageById(mobileDetail.id)

        recyclerView?.let { recyclerView ->
            recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false)
            recyclerView.adapter = MobileImagesListAdapter().also {
                adapter = it
            }
        }
    }

    private fun setView() {
        mobile_detail_page_toolbar.title = getString(R.string.app_name)
        mobile_name.text = mobileDetail.name
        mobile_brand.text = mobileDetail.brand
        mobile_description.text = mobileDetail.description
    }

    override fun setImagesToRecycleView(mobileImages : List<MobileImageResponse>) {
        Log.i("ooooo", mobileImages.toString())
        adapter.addDataArray(mobileImages)
    }
}
