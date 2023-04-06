package com.layfones.composewanandroid.common.http.adapter

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class NetworkResponseAdapter(
    private val successType: Type,
    private val errorHandler: ErrorHandler?
) : CallAdapter<Any, Call<Any>> {
    override fun responseType(): Type {
        return successType
    }

    override fun adapt(call: Call<Any>): Call<Any> {
        return NetworkResponseCall(call, successType as ParameterizedType, errorHandler)
    }
}