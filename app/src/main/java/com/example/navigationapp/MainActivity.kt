package com.example.navigationapp

import android.content.Intent
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.navigationapp.ui.theme.AppTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                TransparentStatusBar()

                Box(
                    Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.background)
                ){
                    val navController  = rememberNavController()
                    NavHost(navController, startDestination = "home"){

                        composable("home"){
                            // define the screen here
                            HomeScreen(
                                onDetailsClick = {
                                        id -> navController.navigate("details/id=$id?name=hi")
                                },
                                onAboutClick = {
                                    navController.navigate("about")
                                }
                            )
                        }

                        composable("about"){
//
                            val mContext  = LocalContext.current
                            mContext.startActivity(Intent(
                                mContext, BottomNavigationActivity::class.java
                            )
                            )
                        }

                        composable("details/id={id}?name={name}",
                            arguments = listOf(
                                navArgument("id"){
                                    type = NavType.LongType
                                },
                                navArgument("name"){
                                    type = NavType.StringType
                                    nullable = true
                                }),
                        ){
                            //inside this composable , I can pick the shared details
                            val arguments = requireNotNull(it.arguments)
                            val id = arguments.getLong("id")
                            val name = arguments.getString("name")

                            DetailsScreen(id, name, onNavigateUp = {
                                navController.popBackStack()
                            })
                        }
                    }
                }
            }



        }
    }


    @Composable
    private fun TransparentStatusBar(){
        val status = MaterialTheme.colors.background
        SideEffect {
            window.statusBarColor = status.toArgb()
            window.navigationBarColor = status.toArgb()
        }
    }
    @Composable
    fun DetailsScreen(id:Long, name:String?, onNavigateUp: () -> Unit){
        // from our lists of articles we use the id to filter and only show the clicked article
        val article = allArticles.first{ it.id == id }
        Scaffold {
           Column(Modifier.padding(it)) {
               Row(
                   verticalAlignment = Alignment.CenterVertically,
                   modifier = Modifier.padding(vertical = 10.dp)
               ) {
                   IconButton(onClick = onNavigateUp) {
                       Icon(Icons.Rounded.ArrowBack, contentDescription = "Go Back")
                   }
               }

               Image(
                   painterResource(article.thumbnail), contentDescription = null,
                   modifier = Modifier
                       .fillMaxWidth()
                       .aspectRatio(16f / 9f), contentScale = ContentScale.Crop
               )
               Spacer(modifier = Modifier.height(20.dp))
               Column(
                   Modifier
                       .fillMaxSize()
                       .padding(horizontal = 16.dp)
               ) {
                   Text(
                       modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center,
                       style = MaterialTheme.typography.body1, text = article.title
                   )
                   Spacer(modifier = Modifier.height(20.dp))
                   Text(
                       modifier = Modifier.fillMaxSize(),
                       style = MaterialTheme.typography.body1, text = article.body
                   )
               }
           }
        }
    }

    @Composable
    fun HomeScreen(onDetailsClick: (id: Long) -> Unit,
                    onAboutClick: () -> Unit
                   ){
        Scaffold {
            LazyColumn(contentPadding = it){
                //to create a single item in a lazy column
                item{
                    HomeAppBar(onAboutClick)
                }
                item {
                    Spacer(modifier = Modifier.height(30.dp))
                }
                items(allArticles){
                    ArticleCard(it, onClick = {
                        onDetailsClick(it.id)
                    } )
                }
            }
        }
    }

    @Composable
    private fun HomeAppBar(onAboutClick: () -> Unit){
         Row(verticalAlignment = Alignment.CenterVertically,
             modifier = Modifier.padding(horizontal = 13.dp, vertical = 10.dp)){
             Text("Celestin\\'S App", style= MaterialTheme.typography.h6)
             Spacer(modifier = Modifier.weight(0.5f))
             TextButton(onClick = onAboutClick) {
                 Text("About")
             }
             val na = LocalUriHandler.current
             Spacer(modifier = Modifier.weight(0.5f))
             TextButton(onClick = {
                 na.openUri("https://www.youtube.com/watch?v=mJ6C0s_GGj4")
             } ){
                 Text("know More")
             }
         }
    }
    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun ArticleCard(item: Content, onClick: () -> Unit){
         Card(
             modifier = Modifier
                 .padding(horizontal = 16.dp, vertical = 10.dp)
                 .fillMaxWidth(),
             onClick = onClick
         ) {
             Column {
               Image(painterResource(id = item.thumbnail),
                    contentDescription = "Thumbnail",
                   modifier = Modifier
                       .fillMaxSize()
                       .aspectRatio(16f / 9f),
                   contentScale = ContentScale.Crop
                   )

                 Column (
                     Modifier
                         .fillMaxWidth()
                         .padding(20.dp)){
                     Text(item.title)
                     Spacer(modifier = Modifier.height(8.dp))
                     Text(item.body, maxLines = 1, style = MaterialTheme.typography.body2)
                 }

             }

         }
    }


    @Composable
    fun AboutScreen(onNavigateUp: () -> Unit){
           // Scaffold
        Scaffold {
            Column(Modifier.padding(it)) {
                AppBar(title="About", onNavigateUp)
                Spacer(Modifier.height(20.dp))
                Column(Modifier.padding(16.dp)) {
                    Text("This is the about page")
                    Spacer(Modifier.height(20.dp))
                    // reference to the current content
                    // when i click the button within this page ,
                    // i will use the LOCALURIHANDLER from jetpack compose to open a web page
                    val na = LocalUriHandler.current
                    // Button jetpack compose
                    Button(onClick = {
                        na.openUri("https://developer.android.com")
                    }){
                        Text("Go to developer.android.com to read more ")
                    }
                }
            }
        }
    }
    @Composable
    fun AppBar(title: String, onNavigateUp: () -> Unit){
        // Row : arranges elements defined in a horizontal format
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 10.dp)
            ){
            // items within the row
            IconButton(onClick = onNavigateUp) {
                Icon(Icons.Rounded.ArrowBack , contentDescription = "Go back")
                Spacer(Modifier.width(10.dp))
                Text(title,style  = MaterialTheme.typography.h6)
            }
        }
    }


}


