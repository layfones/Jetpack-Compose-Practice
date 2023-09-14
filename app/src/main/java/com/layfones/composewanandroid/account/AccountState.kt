package com.layfones.composewanandroid.account

import androidx.navigation.NavHostController
import com.layfones.composewanandroid.data.services.model.User
import com.layfones.composewanandroid.navigation.RoutePath
import com.layfones.composewanandroid.util.showShortToast

sealed interface AccountState {

    object Logout : AccountState

    data class Login(val user: User? = null) : AccountState
}

inline val AccountState.isLogin: Boolean
    get() = this is AccountState.Login


 inline fun AccountState.checkLogin(navHostController: NavHostController, action: (AccountState)->Unit) {
    if (this.isLogin) {
         action(this)
     } else {
         "请先登录".showShortToast()
        navHostController.navigate(RoutePath.login)
     }
 }