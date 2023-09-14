package com.layfones.composewanandroid.ui.components

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.layfones.composewanandroid.navigation.MainScreen

/**
 * @author Leizf
 * @date 2023/9/14
 * @desc
 */
@Composable
fun WanBottomBar(selectedScreen: Int, onClick: (targetIndex: Int) -> Unit) {
    NavigationBar(modifier = Modifier.navigationBarsPadding()) {
        MainScreen.values().forEachIndexed { index, screen ->
            NavigationBarItem(selected = selectedScreen == index,
                onClick = { onClick(index) },
                icon = {
                    Icon(painter = painterResource(if (selectedScreen == index) screen.selectedIconId else screen.unselectedIconId),
                        contentDescription = null,
                        modifier = Modifier.size(if (index == 3) 22.dp else 24.dp))
                },
                label = { Text(stringResource(id = screen.label)) })
        }
    }
}