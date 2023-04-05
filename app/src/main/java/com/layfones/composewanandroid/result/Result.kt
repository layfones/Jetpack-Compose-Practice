package com.layfones.composewanandroid.result

import kotlinx.coroutines.flow.MutableStateFlow


sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()

    data class Error(val throwable: Throwable?) : Result<Nothing>()

    object Loading: Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[throwable=$throwable]"
            Loading -> "Loading"
        }
    }
}

val Result<*>.successed
    get() = this is Result.Success && data != null

fun <T> Result<T>.successOr(fallback: T): T {
    return (this as? Result.Success<T>)?.data ?: fallback
}

val <T> Result<T>.data: T?
    get() = (this as? Result.Success)?.data

inline fun <reified T> Result<T>.updateOnSuccess(stateFlow: MutableStateFlow<T>) {
    if (this is Result.Success) {
        stateFlow.value = data
    }
}