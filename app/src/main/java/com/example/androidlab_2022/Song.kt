package com.example.androidlab_2022

import androidx.annotation.RawRes

data class Song(
    val id: Int,
    val name: String,
    val author: String,
    val time: Int,
    @RawRes val audio: Int,
    val genre: String,
    val cover: String
)