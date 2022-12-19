package com.example.androidlab_2022.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.androidlab_2022.data.entity.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    suspend fun getAll(): List<Task>

    @Query("DELETE FROM task")
    suspend fun deleteAll()

    @Query("SELECT * FROM task WHERE id = :id LIMIT 1")
    suspend fun findTaskById(id: Int): Task

    @Update
    suspend fun update(task: Task)

    @Insert
    suspend fun insert(task: Task)

    @Query("DELETE FROM task WHERE id=:id")
    suspend fun deleteTaskById(id: Int)
}