package com.example.androidlab_2022.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    var title: String,
    var description: String,
    var date: Date?,
    var longitude: Double?,
    var latitude: Double?
)
