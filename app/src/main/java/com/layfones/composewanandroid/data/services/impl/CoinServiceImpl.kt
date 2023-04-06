package com.layfones.composewanandroid.data.services.impl

import com.layfones.composewanandroid.common.http.RetrofitManager
import com.layfones.composewanandroid.data.services.CoinService
import javax.inject.Inject

class CoinServiceImpl @Inject constructor() : CoinService {

    private val service by lazy { RetrofitManager.getService(CoinService::class.java) }

    override suspend fun getMyCoinList(page: Int) = service.getMyCoinList(page)

    override suspend fun getCoinRanking(page: Int) = service.getCoinRanking(page)
}