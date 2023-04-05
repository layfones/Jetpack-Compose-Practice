package com.layfones.composewanandroid.data.repository

import com.layfones.composewanandroid.data.services.AccountService
import javax.inject.Inject

class AccountRepository @Inject constructor(private val service: AccountService) {

    suspend fun login(username: String, password: String) = service.login(username, password)

}