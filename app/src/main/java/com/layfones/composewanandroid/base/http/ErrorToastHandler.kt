package com.layfones.composewanandroid.base.http

import com.layfones.composewanandroid.WanandroidApp
import com.layfones.composewanandroid.base.http.adapter.ErrorHandler
import com.layfones.composewanandroid.util.NetworkUtil
import com.layfones.composewanandroid.util.showShortToast
import java.io.IOException

/**
 * Error的Toast处理
 */
object ErrorToastHandler: ErrorHandler {

    private const val ERROR_DEFAULT = "请求失败"
    private const val ERROR_NET_WORK_DISCONNECTED = "网络连接异常"

    private fun handle(throwable: Throwable):String =
        when (throwable) {
            is IOException -> {
                if (NetworkUtil.isNetworkAvailable(WanandroidApp.app).not()) {
                    ERROR_NET_WORK_DISCONNECTED
                } else ERROR_DEFAULT
            }
            else -> ERROR_DEFAULT
        }


    override fun bizError(code: Int, msg: String) {
        msg.showShortToast()
    }

    override fun otherError(throwable: Throwable) {
        handle(throwable).showShortToast()
    }

}