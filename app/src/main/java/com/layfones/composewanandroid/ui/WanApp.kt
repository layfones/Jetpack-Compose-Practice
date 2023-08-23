package com.layfones.composewanandroid.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.layfones.composewanandroid.navigation.Router
import com.layfones.composewanandroid.ui.screens.coin.CoinScreen
import com.layfones.composewanandroid.ui.screens.collect.CollectScreen
import com.layfones.composewanandroid.ui.screens.login.LoginScreen
import com.layfones.composewanandroid.ui.screens.message.MessageScreen
import com.layfones.composewanandroid.ui.screens.share.ShareListScreen
import com.layfones.composewanandroid.ui.screens.web.WebScreen

@Composable
fun WanApp(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Router.main) {
        composable(route = Router.main) {
            MainScaffold()
        }
        composable(
            route = Router.login,
        ) {
            LoginScreen()
        }
        composable(
            route = Router.coin,
        ) {
            CoinScreen()
        }
        composable(
            route = Router.message,
        ) {
            MessageScreen()
        }
        composable(
            route = "${Router.share}/{userId}",
        ) { backStackEntry ->
            ShareListScreen(backStackEntry.arguments?.getString("userId") ?: "")
        }
        composable(
            route = Router.collect,
        ) {
            CollectScreen()
        }
        composable(
            route = "${Router.web}/{url}",
        ) { backStackEntry ->
            WebScreen(originalUrl = backStackEntry.arguments?.getString("url") ?: "",
                title = backStackEntry.arguments?.getString("title") ?: "")
        }
    }
}