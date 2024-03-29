package com.myapplication.view.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
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
        return presenter.getSortOptionFromDevice()
    }

    private fun showFragmentChoices() {
        val dialogFragment = FragmentDialog(getSortOptionFromDevice(), mSortListener)
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

    override fun manageDataToFavoriteList(detail: MobileDetail) {
        val fragment = sectionsPagerAdapter.getItem(1)
        if (fragment is TabFavoriteMobileFragment) {
            fragment.manageFavoriteList(detail)
        }
        val option = getSortOptionFromDevice()
        presenter.sortFavoriteList(option, getFavoriteList())
    }

    override fun deleteDataFromFavoriteList(detail: MobileDetail) {
        val mobileListfragment = sectionsPagerAdapter.getItem(0)
        if (mobileListfragment is TabMobileListFragment) {
            mobileListfragment.unFavoriteItem(detail)
        }
        val favoriteFragment = sectionsPagerAdapter.getItem(1)
        if (favoriteFragment is TabFavoriteMobileFragment) {
            favoriteFragment.manageFavoriteList(detail)
        }
        presenter.removeFavoriteMobileFromListInDevice(detail.id)
    }

    override fun addFavoriteDataToDevice(id: String) {
        presenter.addFavoriteMobileInListInDevice(id)
    }

    override fun removeFavoriteFromDevice(id: String) {
        presenter.removeFavoriteMobileFromListInDevice(id)
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
        this.showMobileDetailAfterSort(mobileList)
        this.showFavoriteListAfterSort(favList)
    }

    override fun setFavoriteListAndSortFromDevice() {
        val visibility = if (progressBar.visibility == View.GONE) View.VISIBLE else View.GONE
        progressBar.visibility = visibility
        val option = getSortOptionFromDevice()
        presenter.getFavoriteToSetView(getMobileDetailList())
        presenter.sortMobileDetailList(option, getMobileDetailList())
        presenter.sortFavoriteList(option, getFavoriteList())
    }
}