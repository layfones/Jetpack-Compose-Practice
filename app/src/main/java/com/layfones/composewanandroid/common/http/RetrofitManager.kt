package com.layfones.composewanandroid.common.http

import com.layfones.composewanandroid.common.AppLog
import com.layfones.composewanandroid.common.http.adapter.ErrorHandler
import com.layfones.composewanandroid.common.http.adapter.NetworkResponseAdapterFactory
import com.layfones.composewanandroid.common.http.converter.GsonConverterFactory
import com.layfones.composewanandroid.di.ApplicationCoroutineScope
import com.layfones.composewanandroid.di.CoroutinesModule
import com.layfones.composewanandroid.data.services.BaseService
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit

object RetrofitManager {

    const val BASE_URL = "https://www.wanandroid.com"

    private const val TIMEOUT_SECONDS = 10

    var loggingInterceptor = HttpLoggingInterceptor()


    private val client: OkHttpClient
        get() = OkHttpClient.Builder()
            .addInterceptor(SetCookieInterceptor())
            .addInterceptor(CacheCookieInterceptor())
            .addInterceptor(loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .build()
    private val servicesMap = ConcurrentHashMap<String, BaseService>()
    private val errorHandlers = mutableListOf<ErrorHandler>(ErrorToastHandler)


    fun addErrorHandler(handler: ErrorHandler) {
        errorHandlers.add(handler)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : BaseService> getService(serviceClass: Class<T>, baseUrl: String? = null): T {
        return servicesMap.getOrPut(serviceClass.name) {
            Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(NetworkResponseAdapterFactory(object : ErrorHandler {
                    override fun bizError(code: Int, msg: String) {
                        ApplicationCoroutineScope.provideApplicationScope()
                            .launch(CoroutinesModule.providesMainImmediateDispatcher()) {
                                errorHandlers.forEach { it.bizError(code, msg) }
                            }
                        AppLog.d(msg = "bizError: code:$code - msg: $msg")
                    }

                    override fun otherError(throwable: Throwable) {
                        ApplicationCoroutineScope.provideApplicationScope()
                            .launch(CoroutinesModule.providesMainImmediateDispatcher()) {
                                errorHandlers.forEach { it.otherError(throwable) }
                            }
                        AppLog.e(msg = throwable.message.toString(), throwable = throwable)
                    }
                }))
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl ?: BASE_URL)
                .build()
                .create(serviceClass)
        } as T
    }
}