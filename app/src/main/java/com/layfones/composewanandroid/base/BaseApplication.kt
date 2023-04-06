package com.layfones.composewanandroid.base

import android.app.Application
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

abstract class BaseApplication : Application(), ViewModelStoreOwner {

    companion object {
        private lateinit var appViewModelStore: ViewModelStore
    }

    override fun onCreate() {
        super.onCreate()
        appViewModelStore = ViewModelStore()
    }

    override val viewModelStore: ViewModelStore
        get() = appViewModelStore
}