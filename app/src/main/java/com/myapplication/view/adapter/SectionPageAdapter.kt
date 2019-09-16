package com.myapplication.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.myapplication.DataString
import com.myapplication.R
import com.myapplication.view.fragment.TabFavoriteMobileFragment
import com.myapplication.view.fragment.TabMobileListFragment
import kotlinx.android.synthetic.main.custom_tab_layout.view.*
import javax.inject.Inject


class SectionsPagerAdapter @Inject constructor(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    private var tabMobileList = TabMobileListFragment()
    private var tabFavoriteList = TabFavoriteMobileFragment()
    private val pages: Int = 2
    private val tabTitle = arrayOf(DataString.tab1NameString, DataString.tab2NameString)

    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            tabMobileList
        } else {
            tabFavoriteList
        }
    }

    override fun getCount(): Int {
        return pages
    }

    fun getTabView(position: Int): View {
        return LayoutInflater.from(context).inflate(R.layout.custom_tab_layout, null).apply {
            title.text = tabTitle[position]
        }
    }
}