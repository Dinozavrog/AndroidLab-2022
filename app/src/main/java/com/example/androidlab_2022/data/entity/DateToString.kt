package com.example.androidlab_2022.data.entity

import java.text.SimpleDateFormat
import java.util.*

object DateToString {
    fun convertDateToString(date: Date): String {
        val dateFormat = SimpleDateFormat("EEE, MMM d, yyyy", Locale.getDefault())
        return date.let {
            dateFormat.format(it)
        }
    }
}