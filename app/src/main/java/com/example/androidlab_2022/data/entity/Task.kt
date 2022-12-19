package com.example.androidlab_2022.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val title: String,
    val description: String,
    val date: Date?,
    val longitude: Double?,
    val latitude: Double?
)
