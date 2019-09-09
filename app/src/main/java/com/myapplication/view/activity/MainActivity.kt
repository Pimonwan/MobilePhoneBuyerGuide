package com.myapplication.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.myapplication.R
import com.myapplication.view.adapter.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var sectionsPagerAdapter : SectionsPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView(){
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
}
