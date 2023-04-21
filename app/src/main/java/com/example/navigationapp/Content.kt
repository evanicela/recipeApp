package com.example.navigationapp

import androidx.annotation.DrawableRes

val doctor = Content(
    id= 0,
    title = "Several watermelon varieties are grown in Kenya. Some of these varieties are: Sukari F1. hybrid Pato.",
    thumbnail = R.drawable.watermelon,
    body = "Chuck Norris has a diary, it is called the Guinness Book Of World Records.!\n Chuck Norris will kick you, and it will hurt. Forever.\n If you want a list of Chuck Norris' enemies, just check the extinct species list."
)
val group = Content(
    id= 1,
    title = "Types of Fruits,Simple Fruit,Aggregate Fruits,Simple Fruit,Aggregate Fruits,Multiple Fruits,Accessory Fruit",
    thumbnail = R.drawable.fruits,
    body = "Imagine being us!!!"
)
val wearables = Content(
    id= 2,
    title = "Bananas  ?",
    thumbnail = R.drawable.bananas,
    body = "Grown in more than 150 countries, it is widely believed there are more than 1,000 types of bananas in the world, which are subdivided into 50 groups. The most common is the Cavendish, the one most frequently produced for export markets.!!!"
)
val workout = Content(
    id= 3,
    title = " Apples can be sorted into types by sweetness, peel color, date discovered, ripening season, geographical origin, and/or how best to use them.",
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