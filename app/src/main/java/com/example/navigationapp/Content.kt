package com.example.navigationapp

import androidx.annotation.DrawableRes

val doctor = Content(
    id= 0,
    title = "Chuck Norris Jokes ?",
    thumbnail = R.drawable.watermelon,
    body = "Chuck Norris has a diary, it is called the Guinness Book Of World Records.!\n Chuck Norris will kick you, and it will hurt. Forever.\n If you want a list of Chuck Norris' enemies, just check the extinct species list."
)
val group = Content(
    id= 1,
    title = "Liverpool is better, of course!",
    thumbnail = R.drawable.fruits,
    body = "Imagine being us!!!"
)
val wearables = Content(
    id= 2,
    title = "Kotlin Conf ?",
    thumbnail = R.drawable.bananas,
    body = "Kotlin MultiPlatform!!!"
)
val workout = Content(
    id= 3,
    title = " React Native or Kotlin Multiplatform. ?",
    thumbnail = R.drawable.apples,
    body = "Should be discussed 2024 , i think!!!"
)

data class Content(
    val id: Long,
    val title: String,
    @DrawableRes val thumbnail: Int,
    val body: String,
)

//list of the objects
val allArticles  = listOf(doctor, group, wearables, workout)