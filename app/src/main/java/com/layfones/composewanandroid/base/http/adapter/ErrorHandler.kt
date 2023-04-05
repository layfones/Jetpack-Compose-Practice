package com.layfones.composewanandroid.base.http.adapter

interface ErrorHandler {

    fun bizError(code: Int, msg: String)

    fun otherError(throwable: Throwable)

}