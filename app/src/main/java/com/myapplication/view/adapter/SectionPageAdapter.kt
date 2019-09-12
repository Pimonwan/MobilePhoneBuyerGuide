package com.myapplication.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.myapplication.R
import com.myapplication.view.fragment.TabFavoriteMobileFragment
import com.myapplication.view.fragment.TabMobileListFragment
import kotlinx.android.synthetic.main.custom_tab_layout.view.*
import javax.inject.Inject


class SectionsPagerAdapter @Inject constructor(private val context: Context, fm: FragmentManager, val intent: Intent) :
    FragmentPagerAdapter(fm) {

    private var tabMobileList = TabMobileListFragment()
    private var tabFavoriteList = TabFavoriteMobileFragment()
    private val PAGES: Int = 2
    private val TAB_TITLES = arrayOf("MOBILE LIST","FAVORITE LIST")

    override fun getItem(position: Int): Fragment {
        if (position == 0) {
            return tabMobileList
        }else {
            return tabFavoriteList
        }
    }

    override fun getCount(): Int {
        return PAGES
    }

    fun getTabView(position: Int): View {
        return LayoutInflater.from(context).inflate(R.layout.custom_tab_layout, null).apply {
            title.text = TAB_TITLES[position]
        }
    }
}
