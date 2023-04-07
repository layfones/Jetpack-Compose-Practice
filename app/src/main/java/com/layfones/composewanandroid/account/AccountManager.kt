package com.layfones.composewanandroid.account

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import com.layfones.composewanandroid.common.AppLog
import com.layfones.composewanandroid.common.http.SetCookieInterceptor
import com.layfones.composewanandroid.data.services.model.User
import com.layfones.composewanandroid.data.services.model.UserBaseInfo
import com.layfones.composewanandroid.di.ApplicationScope
import com.layfones.composewanandroid.di.IoDispatcher
import com.layfones.composewanandroid.util.fromJson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class AccountManager @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    @ApplicationScope private val applicationScope: CoroutineScope,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    companion object {
        private val PREFERENCE_KEY_ACCOUNT_USER_INFO = stringPreferencesKey("key_account_user_info")
        private val PREFERENCE_KEY_COOKIE = stringPreferencesKey("key_wanandroid_cookie")
    }

    private val userBaseInfoFlow: MutableStateFlow<UserBaseInfo> = MutableStateFlow(UserBaseInfo())

    private val accountStateFlow: MutableStateFlow<AccountState> =
        MutableStateFlow(AccountState.Logout)

    init {
        applicationScope.launch(dispatcher) {
            launch {
                val cookieData = readCookieData()
                if (cookieData.isNotBlank()) {
                    accountStateFlow.tryEmit(AccountState.Login())
                }
            }
            dataStore.data.catch {
                AppLog.e(msg = "Error reading preferences.", throwable = it)
                emit(emptyPreferences())
            }.map {
                if (it.contains(PREFERENCE_KEY_ACCOUNT_USER_INFO)) {
                    Gson().fromJson(it[PREFERENCE_KEY_ACCOUNT_USER_INFO]) ?: UserBaseInfo()
                } else {
                    UserBaseInfo()
                }
            }.collectLatest { userBaseInfo ->
                userBaseInfoFlow.emit(userBaseInfo)
            }
        }
    }

    private fun readCookieData(): String {
        var value = ""
        runBlocking {
            dataStore.data.first {
                value = it[PREFERENCE_KEY_COOKIE] ?: ""
                true
            }
        }
        return value
    }

    fun collectUserInfoFlow(): StateFlow<UserBaseInfo> = userBaseInfoFlow

    fun accountStateFlow(): StateFlow<AccountState> = accountStateFlow

    fun saveUserBaseInfo(userBaseInfo: UserBaseInfo) {
        applicationScope.launch(dispatcher) {
            dataStore.edit {
                it[PREFERENCE_KEY_ACCOUNT_USER_INFO] = Gson().toJson(userBaseInfo).apply {
                    AppLog.d(msg = "${PREFERENCE_KEY_ACCOUNT_USER_INFO.name} cacheUserBaseInfo : $this}")
                }
            }
        }
    }

    fun clearUserBaseInfo() {
        applicationScope.launch(dispatcher) {
            dataStore.edit {
                it[PREFERENCE_KEY_ACCOUNT_USER_INFO] = ""
            }
        }
    }

    fun clearUserCookie() {
        applicationScope.launch(dispatcher) {
            dataStore.edit {
                it[PREFERENCE_KEY_COOKIE] = ""
            }
        }
    }

    fun peekUserBaseInfo(): UserBaseInfo {
        return userBaseInfoFlow.value
    }

    fun login(user: User) {
        applicationScope.launch(dispatcher) {
            accountStateFlow.emit(AccountState.Login(user))
        }
    }

    fun logout() {
        applicationScope.launch(dispatcher) {
            clearUserBaseInfo()
            clearUserCookie()
            accountStateFlow.emit(AccountState.Logout)
        }
    }

    fun isLogin() = accountStateFlow.value.isLogin


}