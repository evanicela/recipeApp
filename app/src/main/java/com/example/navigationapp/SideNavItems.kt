package com.example.navigationapp

sealed class SideNavItems(var route: String, var icon: Int, var title: String ){
    object Home : SideNavItems("home", R.drawable.baseline_home_24, "Home")
    object About : SideNavItems("about", R.drawable.baseline_help_outline_24, "About")
    object Profile : SideNavItems("profile", R.drawable.baseline_supervised_user_circle_24, "Profile")
    object BottomNav : SideNavItems("sidenav", R.drawable.baseline_supervised_user_circle_24, "Bottom Nav")
// you can have more than six items
}
