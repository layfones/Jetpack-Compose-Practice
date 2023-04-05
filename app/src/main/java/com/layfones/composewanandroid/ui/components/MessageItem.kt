package com.layfones.composewanandroid.ui.components

import android.text.Html
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.layfones.composewanandroid.data.services.model.MsgBean

@Composable
fun MessageItem(message: MsgBean, modifier: Modifier) {
    Card(Modifier.padding(horizontal = 12.dp, vertical = 4.dp)) {
        Column(
            modifier
                .padding(16.dp)
                .fillMaxSize()) {
            Row(Modifier.padding(0.dp, 12.dp)) {
                Text(
                    text = message.fromUser,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = message.tag,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = message.niceDate,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Row() {
                Text(text = message.title)
            }
            Row() {
                Text(text = message.message)
            }
        }
    }
}