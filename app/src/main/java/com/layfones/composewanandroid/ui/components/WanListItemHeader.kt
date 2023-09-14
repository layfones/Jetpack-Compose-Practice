package com.layfones.composewanandroid.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun WanListItemHeader(title: String) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(32.dp)
        .background(MaterialTheme.colorScheme.surface)) {
        Text(text = title, textAlign = TextAlign.Center, modifier = Modifier.padding(start = 16.dp).align(Alignment.CenterVertically)
        , style = MaterialTheme.typography.titleSmall.copy(color = MaterialTheme.colorScheme.primary))
    }
}