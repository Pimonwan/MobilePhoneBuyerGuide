package com.myapplication.view.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.myapplication.R
import com.myapplication.presenter.MobileDetailActivityPresenter
import com.myapplication.presenter.displaymodel.MobileDetail
import com.myapplication.presenter.displaymodel.MobileImageUrl
import com.myapplication.presenter.viewInterface.MobileDetailView
import com.myapplication.view.adapter.MobileImagesListAdapter
import kotlinx.android.synthetic.main.activity_mobile_detail.*
import javax.inject.Inject

class MobileDetailActivity : BaseActivity(), MobileDetailView {

    @Inject
    lateinit var presenter: MobileDetailActivityPresenter
    private lateinit var adapter: MobileImagesListAdapter
    private lateinit var mobileDetail: MobileDetail

    private fun initView() {
        mobileDetail = intent.getParcelableExtra("mobileDetail")
        presenter.setView(this)
        presenter.getMobileImageById(mobileDetail.id)
        recyclerView?.let { recyclerView ->
            recyclerView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
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
        mobile_price.text = mobileDetail.price
        mobile_rate.text = mobileDetail.rating
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mobile_detail)
        initView()
        setView()
    }

    override fun setImagesToRecycleView(mobileImages: List<MobileImageUrl>) {
        val visibility = if (progressBar.visibility == View.GONE) View.VISIBLE else View.GONE
        progressBar.visibility = visibility
        adapter.addDataArray(mobileImages)
    }

    override fun showErrorMessageToast(throwable: Throwable) {
        val message = "something happend \n" + throwable.message.toString()
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
