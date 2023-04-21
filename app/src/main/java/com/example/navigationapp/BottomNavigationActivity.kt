package com.example.navigationapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.navigationapp.BottomNavigationItems.About.route
import com.example.navigationapp.ui.theme.NavigationAppTheme

class BottomNavigationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
             MainScreen()
        }
    }

    override fun onBackPressed() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}

@Composable
fun MainScreen(){
    val navController = rememberNavController()
    Scaffold(
        topBar = { TopBar()},
        bottomBar = { BottomNavigationBar(navController)},
        content = {
            Box(modifier = Modifier.padding(it)) {
                Navigation(navController = navController)
            }
        },
        backgroundColor = colorResource(id = R.color.yellow)
    ) 
}

@Composable
fun Navigation(navController: NavHostController){
     NavHost(navController , startDestination = BottomNavigationItems.Home.route){
         composable(BottomNavigationItems.Home.route){
             HomeScreen()
         }
         composable(BottomNavigationItems.About.route){
             AboutScreen()
         }
         composable(BottomNavigationItems.Profile.route){
             ProfileScreen()
         }
     }
}

@Preview(showBackground = true)
@Composable
fun MainPreview(){
    MainScreen()
}

@Composable
fun TopBar() {
    TopAppBar(
       title = {
           Text(text = stringResource(R.string.bnv), fontSize = 18.sp)
       } ,
        backgroundColor = colorResource(id = R.color.yellow) ,
        contentColor = Color.Black
    ) 
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview(){
    TopBar()
}

@Composable
fun BottomNavigationBar(navController: NavController){
    // create a list of the items from the sealed class
    // loop each item to create the view for it
    val items = listOf(BottomNavigationItems.Home, BottomNavigationItems.About, BottomNavigationItems.Profile, BottomNavigationItems.SideNav)
    BottomNavigation(
        backgroundColor = colorResource(id = R.color.yellow),
        contentColor = Color.Black
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        //tag for active page/ requested page
        val currentRoute = navBackStackEntry?.destination?.route
        //loop our items for the bottom navigation
        items.forEach{
            //
            BottomNavigationItem(
                selected = false,
                icon = { Icon(painterResource(id = it.icon), contentDescription = it.title )}, label = {Text(it.title)}, alwaysShowLabel = true,
                selectedContentColor = Color.Black, unselectedContentColor = Color.Black.copy(0.4f),
                onClick = {
//                    handle navigation here
                    navController.navigate(it.route){
                        navController.graph.startDestinationRoute?.let {
                            // create stack i.e. whats on top of the stack
                            // and whats below it
                            // create the navigation protocol
                            popUpTo(route){
                                saveState = true
                            }
                        }
                        // avoid multiple copies of the same view
                        // especially when reselecting the same item
                        launchSingleTop = true
                        // restoring the state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomBar(){
//    BottomNavigationBar()
}


























