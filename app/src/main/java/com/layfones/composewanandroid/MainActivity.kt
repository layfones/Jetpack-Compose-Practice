package com.layfones.composewanandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.layfones.composewanandroid.navigation.LocalNavController
import com.layfones.composewanandroid.ui.WanApp
import com.layfones.composewanandroid.ui.WanAppState
import com.layfones.composewanandroid.ui.createAppStateViewModel
import com.layfones.composewanandroid.ui.theme.ComposeWanandroidTheme
import com.layfones.composewanandroid.ui.theme.WanandroidTheme
import com.layfones.composewanandroid.util.WebViewManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        //WebView预加载
        WebViewManager.prepare(applicationContext)
        setContent {

            val systemUiController = rememberSystemUiController()
            val wanAppState: WanAppState = createAppStateViewModel()
            val useDarkIcons = when (wanAppState.theme) {
                WanandroidTheme.LIGHT -> true
                WanandroidTheme.DARK -> false
                WanandroidTheme.SYSTEM -> !isSystemInDarkTheme()
            }
            DisposableEffect(systemUiController, useDarkIcons) {
                systemUiController.setSystemBarsColor(
                    color = Color.Transparent,
                    darkIcons = useDarkIcons
                )
                onDispose {}
            }
            ComposeWanandroidTheme(wanAppState.theme) {
                val navController = rememberAnimatedNavController()
                CompositionLocalProvider(LocalNavController provides navController) {
                    WanApp(navController)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        WebViewManager.destroy()
    }
}