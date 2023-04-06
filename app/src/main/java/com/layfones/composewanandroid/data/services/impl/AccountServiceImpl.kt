package com.layfones.composewanandroid.data.services.impl


import com.layfones.composewanandroid.common.http.RetrofitManager
import com.layfones.composewanandroid.data.services.AccountService
import javax.inject.Inject

class AccountServiceImpl @Inject constructor() : AccountService {

    private val service by lazy { RetrofitManager.getService(AccountService::class.java) }

    override suspend fun login(username: String, password: String) =
        service.login(username, password)

    override suspend fun logout() = service.logout()

    override suspend fun register(
        username: String,
        password: String,
        confirmPassword: String
    ) = service.register(username, password, confirmPassword)

    override suspend fun getUserInfo() = service.getUserInfo()
}