package com.layfones.composewanandroid.ui.components

import android.text.Html
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.layfones.composewanandroid.data.services.model.CollectBean

@Composable
fun CollectItem(collect: CollectBean, modifier: Modifier) {
    Card(Modifier.padding(horizontal = 12.dp, vertical = 4.dp)) {
        Column(
            modifier
                .padding(16.dp)
                .fillMaxSize()) {
            Row(Modifier.padding(0.dp, 12.dp)) {
                Text(
                    text = Html.fromHtml(collect.title).toString(),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Row() {
                val text = if (collect.author.isEmpty()) {
                    "分类·" + collect.chapterName
                } else {
                    "作者·" + collect.author
                }
                Text(text = text)
            }
        }
    }
}