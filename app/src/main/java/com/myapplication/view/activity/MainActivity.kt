package com.myapplication.view.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
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
import com.myapplication.view.viewInterface.SortButtonInterface
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.get

class MainActivity : AppCompatActivity(), MainActivityView, ItemListClick.OnClickFavoriteButton {

    private val TAG = "0"
    private var fragmentTransaction : FragmentTransaction = supportFragmentManager.beginTransaction()
    private var bundle : Bundle = Bundle()
    private var priorInstance: Fragment? = supportFragmentManager.findFragmentByTag(TAG)

    private lateinit var sectionsPagerAdapter : SectionsPagerAdapter
    private var presenter : MainActivityPresenter = get()

    private val mSortListener: SortButtonInterface = object : SortButtonInterface {
        override fun sortData(sortType: String) {
            presenter.sortMobileDetailList(sortType, getMobileDetailList())
            presenter.sortFavoriteList(sortType, getFavoriteList())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView(){
        presenter.setView(this)
        sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager, intent)
        view_pager.adapter = sectionsPagerAdapter

        tabs.setupWithViewPager(view_pager)

        for (i in 0 until tabs.tabCount) {
            val tab: TabLayout.Tab? = tabs.getTabAt(i)
            tab!!.customView = sectionsPagerAdapter.getTabView(i)
        }

        toolbar.title = getString(R.string.app_name)
        setSupportActionBar(toolbar)
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

    private fun showFragmentChoices() {
        val dialogFragment = FragmentDialog(mSortListener)
        dialogFragment.arguments = bundle
        fragmentTransaction = supportFragmentManager.beginTransaction()
        if (priorInstance != null) {
            fragmentTransaction.remove(priorInstance!!)
        }
        fragmentTransaction.addToBackStack(null)
        dialogFragment.show(fragmentTransaction, TAG)
    }

    fun getMobileDetailList() : List<MobileDetail>{
        var data = listOf<MobileDetail>()
        val fragment = sectionsPagerAdapter.getItem(0)
        if (fragment is TabMobileListFragment) {
            data = fragment.getMobileDetailList()
        }
        return data
    }

    fun getFavoriteList() : List<MobileDetail>{
        var data = listOf<MobileDetail>()
        val fragment = sectionsPagerAdapter.getItem(1)
        if (fragment is TabFavoriteMobileFragment) {
            data = fragment.getFavoriteList()
        }
        return data
    }


    override fun addDataToFavoriteList(detail: MobileDetail) {
        val fragment = sectionsPagerAdapter.getItem(1)
        if (fragment is TabFavoriteMobileFragment) {
            fragment.manageFavoriteList(detail)
        }
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

}
