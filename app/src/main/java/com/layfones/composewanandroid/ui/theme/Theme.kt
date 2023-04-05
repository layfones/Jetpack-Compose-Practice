package com.layfones.composewanandroid.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColorScheme()

private val LightColorPalette = lightColorScheme()

enum class WanandroidTheme(val named: String) {
    LIGHT("关闭"), DARK("开启"), SYSTEM("跟随系统")
}

@Composable
fun ComposeWanandroidTheme(
    theme: WanandroidTheme = WanandroidTheme.LIGHT,
    content: @Composable () -> Unit
) {

    val colors = when (theme) {
        WanandroidTheme.LIGHT -> LightColorPalette
        WanandroidTheme.DARK -> DarkColorPalette
        WanandroidTheme.SYSTEM -> if (isSystemInDarkTheme()) DarkColorPalette else LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}