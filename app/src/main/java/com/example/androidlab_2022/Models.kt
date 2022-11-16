package com.example.androidlab_2022

sealed class Models(val id: Int) {
    data class Card(val description: String, var title: String): Models(0)
    data class Rekalama(val title: String, val imageUrl: String): Models(0)

}