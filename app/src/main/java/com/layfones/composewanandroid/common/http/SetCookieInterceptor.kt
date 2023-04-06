package com.layfones.composewanandroid.common.http

import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class SetCookieInterceptor : Interceptor {

    private val dataStore = DataStoreFactory.getDefaultPreferencesDataStore()

    companion object {
        private val PREFERENCE_KEY_COOKIE = stringPreferencesKey("key_wanandroid_cookie")
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        val domain = request.url.host
        //获取domain内的cookie
        if (domain.isNotEmpty()) {
            val cookie: String = readCookieData()
            if (cookie.isNotEmpty()) {
                builder.addHeader("Cookie", cookie)
            }
        }
        return chain.proceed(builder.build())
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
}