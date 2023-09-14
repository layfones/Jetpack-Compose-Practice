package com.layfones.composewanandroid.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.layfones.composewanandroid.R

/**
 * @author Leizf
 * @date 2023/9/14
 * @desc
 */
enum class MainScreen(@StringRes val label: Int,
                      @DrawableRes val unselectedIconId: Int,
                      @DrawableRes val selectedIconId: Int) {
    Home(R.string.home, R.drawable.ic_home, R.drawable.ic_home), Project(R.string.project,
        R.drawable.ic_project,
        R.drawable.ic_project),
    Navigator(R.string.navigator,
        R.drawable.ic_navigation,
        R.drawable.ic_navigation),
    Group(R.string.group, R.drawable.ic_group, R.drawable.ic_group), Profile(R.string.profile,
        R.drawable.ic_person,
        R.drawable.ic_person)
}