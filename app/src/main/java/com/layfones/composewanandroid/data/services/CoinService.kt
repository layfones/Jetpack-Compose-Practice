package com.layfones.composewanandroid.data.services

import com.layfones.composewanandroid.common.http.adapter.NetworkResponse
import com.layfones.composewanandroid.data.services.model.CoinHistory
import com.layfones.composewanandroid.data.services.model.CoinInfo
import com.layfones.composewanandroid.data.services.model.PageResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface CoinService : BaseService {

    @GET("/lg/coin/list/{page}/json")
    suspend fun getMyCoinList(@Path("page") page: Int): NetworkResponse<PageResponse<CoinHistory>>

    @GET("coin/rank/{page}/json")
    suspend fun getCoinRanking(@Path("page") page: Int): NetworkResponse<PageResponse<CoinInfo>>

}