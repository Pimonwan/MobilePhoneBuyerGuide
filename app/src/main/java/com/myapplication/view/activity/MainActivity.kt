package com.myapplication.view.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.tabs.TabLayout
import com.myapplication.R
import com.myapplication.presenter.MainActivityPresenter
import com.myapplication.presenter.displaymodel.MobileDetail
import com.myapplication.presenter.viewInterface.MainActivityView
import com.myapplication.view.adapter.SectionsPagerAdapter
import com.myapplication.view.fragment.FragmentDialog
import com.myapplication.view.fragment.TabFavoriteMobileFragment
import com.myapplication.view.fragment.TabMobileListFragment
import com.myapplication.view.viewInterface.ItemListClick
import com.myapplication.view.viewInterface.SetDataFromDevice
import com.myapplication.view.viewInterface.SortButtonInterface
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainActivityView, ItemListClick.OnClickFavoriteButton, SetDataFromDevice {

    @Inject
    lateinit var presenter: MainActivityPresenter
    private val tag = "0"
    private var fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
    private var bundle: Bundle = Bundle()
    private var priorInstance: Fragment? = supportFragmentManager.findFragmentByTag(tag)
    private lateinit var sectionsPagerAdapter: SectionsPagerAdapter
    private val mSortListener: SortButtonInterface = object : SortButtonInterface {
        override fun sortData(sortType: String) {
            presenter.setSortOptionInDevice(sortType)
            presenter.sortMobileDetailList(sortType, getMobileDetailList())
            presenter.sortFavoriteList(sortType, getFavoriteList())
        }
    }

    private fun initView() {
        presenter.setView(this, this)
        sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)
        for (i in 0 until tabs.tabCount) {
            val tab: TabLayout.Tab? = tabs.getTabAt(i)
            tab!!.customView = sectionsPagerAdapter.getTabView(i)
        }
        toolbar.title = getString(R.string.app_name)
        setSupportActionBar(toolbar)
    }

    private fun getSortOptionFromDevice() : String{
        val option = presenter.getSortOptionFromDevice()
        return option
    }

    private fun showFragmentChoices() {
        val dialogFragment = FragmentDialog(mSortListener)
        dialogFragment.arguments = bundle
        fragmentTransaction = supportFragmentManager.beginTransaction()
        if (priorInstance != null) {
            fragmentTransaction.remove(priorInstance!!)
        }
        fragmentTransaction.addToBackStack(null)
        dialogFragment.show(fragmentTransaction, tag)
    }

    private fun getMobileDetailList(): List<MobileDetail> {
        var data = listOf<MobileDetail>()
        val fragment = sectionsPagerAdapter.getItem(0)
        if (fragment is TabMobileListFragment) {
            data = fragment.getMobileDetailList()
        }
        return data
    }

    private fun getFavoriteList(): List<MobileDetail> {
        var data = listOf<MobileDetail>()
        val fragment = sectionsPagerAdapter.getItem(1)
        if (fragment is TabFavoriteMobileFragment) {
            data = fragment.getFavoriteList()
        }
        return data
    }

    private fun unFavoriteMobileList(data : MobileDetail){
        val fragment = sectionsPagerAdapter.getItem(0)
        if (fragment is TabMobileListFragment) {
            fragment.unFavoriteMobileDetailList(data)
        }
        presenter.removeFavoriteMobileFromListInDevice(data.id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val mMenuInflater: MenuInflater = menuInflater
        mMenuInflater.inflate(R.menu.toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sort_menu -> {
                showFragmentChoices()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun addDataToFavoriteList(detail: MobileDetail) {
        val fragment = sectionsPagerAdapter.getItem(1)
        if (fragment is TabFavoriteMobileFragment) {
            fragment.manageFavoriteList(detail)
        }
        val option = getSortOptionFromDevice()
        presenter.sortFavoriteList(option, getFavoriteList())
        presenter.addFavoriteMobileInListInDevice(detail.id)
    }

    override fun deleteDataFromFavoriteList(detail: MobileDetail) {
        unFavoriteMobileList(detail)
    }

    override fun showMobileDetailAfterSort(list: List<MobileDetail>) {
        val mobileDetailFragment = sectionsPagerAdapter.getItem(0)
        if (mobileDetailFragment is TabMobileListFragment) {
            mobileDetailFragment.setDataArray(list)
        }
    }

    override fun showFavoriteListAfterSort(list: List<MobileDetail>) {
        val favoriteMobileFragment = sectionsPagerAdapter.getItem(1)
        if (favoriteMobileFragment is TabFavoriteMobileFragment) {
            favoriteMobileFragment.setDataArray(list)
        }
    }

    override fun showFavoriteFromDevice(mobileList: List<MobileDetail>, favList: List<MobileDetail>) {
        val mobileDetailFragment = sectionsPagerAdapter.getItem(0)
        if (mobileDetailFragment is TabMobileListFragment) {
            mobileDetailFragment.setDataArray(mobileList)
        }
        val favoriteMobileFragment = sectionsPagerAdapter.getItem(1)
        if (favoriteMobileFragment is TabFavoriteMobileFragment) {
            favoriteMobileFragment.setDataArray(favList)
        }
    }

    override fun setFavoriteListAndSortFromDevice() {
        val option = getSortOptionFromDevice()
        presenter.getFavoriteToSetView(getMobileDetailList())
        presenter.sortMobileDetailList(option, getMobileDetailList())
        presenter.sortFavoriteList(option, getFavoriteList())
    }

}