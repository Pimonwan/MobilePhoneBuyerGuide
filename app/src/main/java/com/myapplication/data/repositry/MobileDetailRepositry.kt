package com.myapplication.data.repositry

import android.util.Log
import com.myapplication.data.domainInterface.MobileDetailDomain
import com.myapplication.data.model.MobileDetailResponse
import com.myapplication.data.remote.APIService
import com.myapplication.data.remote.RetrofitClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MobileDetailRepositry {

    private lateinit var domain: MobileDetailDomain
    private lateinit var apiService: APIService
    private lateinit var mobile : Call<List<MobileDetailResponse>>

    fun initial(domain : MobileDetailDomain) {
        this.domain = domain
    }


    fun getMobileDetail(){
        apiService = RetrofitClass.getAPIService()
        mobile = apiService.getMobileDetail()
        mobile.enqueue(object : Callback<List<MobileDetailResponse>> {
            override fun onFailure(call: Call<List<MobileDetailResponse>>, t: Throwable) {
            }

            override fun onResponse(call: Call<List<MobileDetailResponse>>, response: Response<List<MobileDetailResponse>>) {
                val result = response.body()!!
                Log.i("iiii", result[0].name)
                domain.mapMobileDetailData(result)
            }
        })
    }
}