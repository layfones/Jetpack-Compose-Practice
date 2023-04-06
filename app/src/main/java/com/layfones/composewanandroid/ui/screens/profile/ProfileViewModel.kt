package com.layfones.composewanandroid.ui.screens.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.layfones.composewanandroid.account.AccountState
import com.layfones.composewanandroid.account.IAccountViewModelDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val accountViewModelDelegate: IAccountViewModelDelegate) :
    ViewModel(), IAccountViewModelDelegate by accountViewModelDelegate {

    init {
        onLogin()
    }

    val github = "https://github.com/layfones/Jetpack-Compose-Practice"

    private fun onLogin() {
        viewModelScope.launch {
            accountState.collect {
                when (it) {
                    is AccountState.Login -> fetchUserInfo()
                    AccountState.Logout -> {}
                }
            }
        }
    }

    fun userLogout() {
        viewModelScope.launch {
            logout()
        }
    }
}