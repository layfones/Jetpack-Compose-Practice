package com.layfones.composewanandroid.common.http.exception

import java.io.IOException

class CallCancelException(throwable: Throwable):IOException(throwable) {

    fun isSuppressed() = true

}