package com.layfones.composewanandroid.ui

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.layfones.composewanandroid.navigation.Router
import com.layfones.composewanandroid.ui.screens.coin.CoinScreen
import com.layfones.composewanandroid.ui.screens.collect.CollectScreen
import com.layfones.composewanandroid.ui.screens.login.LoginScreen
import com.layfones.composewanandroid.ui.screens.message.MessageScreen
import com.layfones.composewanandroid.ui.screens.share.ShareListScreen
import com.layfones.composewanandroid.ui.screens.web.WebScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun WanApp(navController: NavHostController) {
    AnimatedNavHost(navController = navController, startDestination = Router.main) {
        composable(route = Router.main) {
            MainScaffold()
        }
        composable(
            route = Router.login,
            enterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Up, tween(350))
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Down, tween(350))
            }) {
            LoginScreen()
        }
        composable(
            route = Router.coin,
            enterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Left, tween(350))
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, tween(350))
            }) {
            CoinScreen()
        }
        composable(
            route = Router.message,
            enterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Left, tween(350))
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, tween(350))
            }) {
            MessageScreen()
        }
        composable(
            route = "${Router.share}/{userId}",
            enterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Left, tween(350))
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, tween(350))
            }) { backStackEntry ->
            ShareListScreen(backStackEntry.arguments?.getString("userId") ?: "")
        }
        composable(
            route = Router.collect,
            enterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Left, tween(350))
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, tween(350))
            }) {
            CollectScreen()
        }
        composable(
            route = "${Router.web}/{url}",
            enterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Left, tween(350))
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, tween(350))
            }) { backStackEntry ->
            WebScreen(
                originalUrl = backStackEntry.arguments?.getString("url") ?: "",
                title = backStackEntry.arguments?.getString("title") ?: ""
            )
        }
    }
}