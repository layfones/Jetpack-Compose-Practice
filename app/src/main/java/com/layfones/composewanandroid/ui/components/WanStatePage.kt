package com.layfones.composewanandroid.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.layfones.composewanandroid.R

@Composable
fun WanStatePage(loading: Boolean, empty: Boolean, content: @Composable () -> Unit) {
    Box(Modifier.fillMaxSize()) {
        content()
        if (loading && empty) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (!loading && empty) {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(Modifier.align(Alignment.Center)) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_empty),
                        contentDescription = "没有数据",
                        Modifier
                            .align(Alignment.CenterHorizontally)
                            .size(70.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(text = "没有数据",
                        Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 12.dp), style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.primary))
                }

            }
        }
    }

}