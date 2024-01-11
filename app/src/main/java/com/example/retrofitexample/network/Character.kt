package com.example.retrofitexample.network

import android.media.Image
import com.squareup.moshi.Json

data class Character(
    // with json u can speicifed whats named in json file.the name that has written in json file.
    @Json(name="name")
    val name: String,
    @Json(name="image")
    val image: String

)
data class CharacterResponse(@Json(name="results")
val result: List<Character>)