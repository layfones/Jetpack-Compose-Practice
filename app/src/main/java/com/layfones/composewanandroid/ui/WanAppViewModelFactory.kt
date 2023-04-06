package com.layfones.composewanandroid.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.layfones.composewanandroid.data.usecase.ArticleCollectUseCase
import javax.inject.Inject

class WanAppViewModelFactory @Inject constructor(
    private val application: Application,
    private val articleCollectUseCase: ArticleCollectUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return when (modelClass) {
            WanAppViewModel::class.java -> WanAppViewModel(application, articleCollectUseCase)
            else -> throw IllegalArgumentException("Unknown class $modelClass")
        } as T
    }

}