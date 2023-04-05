package com.layfones.composewanandroid.ui.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.layfones.composewanandroid.account.IAccountViewModelDelegate
import com.layfones.composewanandroid.account.LocalUserInfo
import com.layfones.composewanandroid.base.http.adapter.isSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val accountViewModelDelegate: IAccountViewModelDelegate) :
    ViewModel(), IAccountViewModelDelegate by accountViewModelDelegate {


    var viewState by mutableStateOf(LoginViewState())
        private set

    private val _viewEvents = Channel<LoginViewEvent>(Channel.BUFFERED)
    val viewEvents = _viewEvents.receiveAsFlow()

    fun login() {
        viewModelScope.launch {
            val login =
                accountViewModelDelegate.login(LocalUserInfo(viewState.account, viewState.password))
            if (login.isSuccess) {
                _viewEvents.send(LoginViewEvent.PopBack)
            }
        }
    }

    fun onAccountValueChange(value: String) {
        viewState = viewState.copy(account = value)
    }

    fun onPasswordValueChange(value: String) {
        viewState = viewState.copy(password = value)
    }

}

data class LoginViewState(
    val account: String = "layfones",
    val password: String = "As228743029,",
    val isLoading: Boolean = false
)

sealed class LoginViewEvent {
    object PopBack : LoginViewEvent()
    data class Error(val message: String) : LoginViewEvent()
}