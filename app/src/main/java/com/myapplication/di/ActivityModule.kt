package com.myapplication.di

import com.myapplication.view.activity.MainActivity
import com.myapplication.view.activity.MobileDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeMobileDetailActivity(): MobileDetailActivity
}