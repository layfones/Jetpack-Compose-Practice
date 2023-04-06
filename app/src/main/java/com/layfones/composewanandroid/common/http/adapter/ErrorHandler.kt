package com.layfones.composewanandroid.common.http.adapter

interface ErrorHandler {

    fun bizError(code: Int, msg: String)

    fun otherError(throwable: Throwable)

}