package com.myapplication.di

import com.myapplication.presenter.MainActivityPresenter
import org.koin.dsl.module

val appModule = module {
    single { MainActivityPresenter() }
}