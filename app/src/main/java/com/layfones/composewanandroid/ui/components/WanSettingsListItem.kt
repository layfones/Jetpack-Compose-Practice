package com.layfones.composewanandroid.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun WanSettingsListItem(
    @DrawableRes leadingIcon: Int,
    headlineText: String,
    modifier: Modifier = Modifier,
    supportingText: @Composable (() -> Unit)? = null
) {
    ListItem(
        leadingContent = {
            androidx.compose.material3.Icon(
                painter = painterResource(leadingIcon),
                contentDescription = ""
            )
        },
        // trailingContent = {
        //     Icon(
        //         imageVector = Icons.Default.KeyboardArrowRight,
        //         contentDescription = "Enter",
        //         modifier = Modifier.size(24.dp),
        //         tint = MaterialTheme.colorScheme.onSurface
        //     )
        // },

        headlineContent = {
            Text(text = headlineText)
        },
        supportingContent = supportingText,
        modifier = modifier,
        colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.surface)
    )
}