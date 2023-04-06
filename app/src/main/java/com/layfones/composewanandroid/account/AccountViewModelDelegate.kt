package com.layfones.composewanandroid.account

import com.layfones.composewanandroid.common.http.adapter.NetworkResponse
import com.layfones.composewanandroid.common.http.adapter.isSuccess
import com.layfones.composewanandroid.common.http.adapter.whenSuccess
import com.layfones.composewanandroid.data.services.AccountService
import com.layfones.composewanandroid.data.services.model.User
import com.layfones.composewanandroid.data.services.model.UserBaseInfo
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

interface IAccountViewModelDelegate {
    val accountState: StateFlow<AccountState>
    val accountInfo: StateFlow<UserBaseInfo>
    val isLogin: Boolean
    val userId: String
    suspend fun fetchUserInfo(): NetworkResponse<UserBaseInfo>
    suspend fun login(loginParams: LoginParams): NetworkResponse<User>
    suspend fun logout(): NetworkResponse<Any>
    suspend fun register(registerParams: RegisterParams): NetworkResponse<Any>
}

internal class AccountViewModelDelegate @Inject constructor(
    private val service: AccountService,
    private val accountManager: AccountManager
) : IAccountViewModelDelegate {

    override val accountState: StateFlow<AccountState>
        get() = accountManager.accountStateFlow()

    override val accountInfo: StateFlow<UserBaseInfo>
        get() = accountManager.collectUserInfoFlow()

    override val isLogin: Boolean
        get() = accountManager.isLogin()

    override val userId: String
        get() = accountManager.collectUserInfoFlow().value.userInfo.id

    override suspend fun fetchUserInfo(): NetworkResponse<UserBaseInfo> {
        return service.getUserInfo().also {
            it.whenSuccess { userBaseInfo ->
                accountManager.saveUserBaseInfo(userBaseInfo)
            }
        }
    }

    override suspend fun login(loginParams: LoginParams): NetworkResponse<User> {
        val result = service.login(loginParams.username, loginParams.password)
        result.whenSuccess { user ->
            accountManager.login(user)
        }
        return result
    }

    override suspend fun logout(): NetworkResponse<Any> {
        return service.logout().also {
            if (it.isSuccess) {
                accountManager.logout()
            }
        }
    }

    override suspend fun register(registerParams: RegisterParams): NetworkResponse<Any> {
        return service.register(
            registerParams.username,
            registerParams.password,
            registerParams.confirmPassword
        )
    }
}