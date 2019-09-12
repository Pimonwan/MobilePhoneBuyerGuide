package com.myapplication.di

import com.myapplication.view.fragment.TabFavoriteMobileFragment
import com.myapplication.view.fragment.TabMobileListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeMobileListFragment(): TabMobileListFragment

    @ContributesAndroidInjector
    abstract fun contributeFavoriteFragment(): TabFavoriteMobileFragment

}