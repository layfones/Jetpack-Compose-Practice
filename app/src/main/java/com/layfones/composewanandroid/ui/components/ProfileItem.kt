package com.layfones.composewanandroid.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ProfileItem(icon: Painter, title: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp, 0.dp)
            .height(58.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(painter = icon, contentDescription = title, modifier = Modifier.size(28.dp).padding(top = 4.dp))
        Text(text = title, modifier = Modifier.padding(12.dp, 0.dp))
    }
}