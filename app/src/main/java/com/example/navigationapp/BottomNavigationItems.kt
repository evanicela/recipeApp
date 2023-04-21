package com.example.navigationapp

import com.example.navigationapp.BottomNavigationItems.About.route
import kotlin.coroutines.coroutineContext

sealed class BottomNavigationItems(var route: String, var icon: Int, var title: String ){
    object Home : BottomNavigationItems("home", R.drawable.baseline_home_24, "Home")
    object About : BottomNavigationItems("about", R.drawable.baseline_help_outline_24, "About")
    object Profile : BottomNavigationItems("profile", R.drawable.baseline_supervised_user_circle_24, "Profile")
    object SideNav : BottomNavigationItems("profile", R.drawable.baseline_supervised_user_circle_24, "Profile")
}
