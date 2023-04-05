package com.layfones.composewanandroid.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.layfones.composewanandroid.R
import com.layfones.composewanandroid.navigation.LocalNavController
import com.layfones.composewanandroid.ui.components.BackButton
import kotlinx.coroutines.flow.collect

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun LoginScreen(viewModel: LoginViewModel = hiltViewModel()) {

    val navHostController = LocalNavController.current
    var passwordHidden by rememberSaveable { mutableStateOf(true) }
    val viewState = viewModel.viewState

    LaunchedEffect(Unit) {
        viewModel.viewEvents.collect {
            if (it is LoginViewEvent.PopBack) {
                navHostController.popBackStack()
            } else {

            }
        }
    }

    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(Color.Cyan)
            .systemBarsPadding()
    ) {
        item {
            BackButton() {
                navHostController.popBackStack()
            }
        }
        item {
            Image(
                painter = painterResource(id = R.mipmap.ic_launcher_round),
                contentDescription = "",
                modifier = Modifier
                    .padding(0.dp, 60.dp, 0.dp, 30.dp)
                    .fillMaxWidth()
                    .height(100.dp),
                alignment = Alignment.Center
            )
        }
        item {
            OutlinedTextField(
                value = viewState.account,
                label = { Text("用户名") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(36.dp, 6.dp),
                onValueChange = {
                    viewModel.onAccountValueChange(it)
                })

        }
        item {
            OutlinedTextField(
                value = viewState.password,
                label = { Text("密码") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(36.dp, 6.dp),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            passwordHidden = !passwordHidden
                        }) {
                        Icon(
                            painter = painterResource(id = com.google.android.material.R.drawable.design_ic_visibility),
                            contentDescription = ""
                        )
                    }
                },
                visualTransformation = if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
                onValueChange = {
                    viewModel.onPasswordValueChange(it)
                })
        }
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(36.dp, 12.dp), horizontalAlignment = Alignment.End
            ) {
                Button(modifier = Modifier.width(90.dp), onClick = {
                    viewModel.login()
                }) {
                    Text(text = "登录")
                }
            }

        }
    }
}