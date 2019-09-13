package com.myapplication.data.repositry

import com.myapplication.data.remote.RetrofitClass
import org.junit.Test

class MobileDetailRepositryTest {

    private val api = RetrofitClass()
    private val mobileDetailRepositry = MobileDetailRepositry(api)

    @Test
    fun test() {
        mobileDetailRepositry.getMobileObservable()
    }
}