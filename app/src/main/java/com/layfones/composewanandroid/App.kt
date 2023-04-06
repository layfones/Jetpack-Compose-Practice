package com.layfones.composewanandroid

import com.layfones.composewanandroid.base.BaseApplication
import com.layfones.composewanandroid.common.http.DataStoreFactory
import com.layfones.composewanandroid.util.DataStoreUtils
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : BaseApplication() {

    companion object {
        lateinit var app: App
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        DataStoreFactory.init(this)
        DataStoreUtils.init(this)
    }


}