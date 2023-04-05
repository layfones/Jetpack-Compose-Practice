package com.layfones.composewanandroid

import android.app.Application
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.layfones.composewanandroid.base.http.DataStoreFactory
import com.layfones.composewanandroid.util.DataStoreUtils
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WanandroidApp : Application(), ViewModelStoreOwner {

    companion object {
        lateinit var app: WanandroidApp
        lateinit var appViewModelStore: ViewModelStore
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        appViewModelStore = ViewModelStore()
        DataStoreFactory.init(this)
        DataStoreUtils.init(this)
    }

    override val viewModelStore: ViewModelStore
        get() = appViewModelStore

}