package com.example.androidlab_2022.data

import android.content.Context
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.room.Room
import com.example.androidlab_2022.data.entity.ConvertDate
import com.example.androidlab_2022.data.entity.Task
import com.example.androidlab_2022.ui.TasksFragment

class TasksRep(context: Context) {

    private val db by lazy {
        Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .addTypeConverter(ConvertDate())
            .build()
    }

    private val taskDao by lazy {
        db.getTaskDao()
    }

    suspend fun insertTask(task: Task) {
        taskDao.insert(task)
    }

    suspend fun deleteTask(id: Int) {
        taskDao.deleteTaskById(id)
    }

    suspend fun getTasks() : List<Task> =
        taskDao.getAll()


    suspend fun deleteAllTasks() {
        taskDao.deleteAll()
    }

    suspend fun findTask(id: Int) {
        taskDao.findTaskById(id)
    }

    suspend fun updateTask(task : Task) {
        taskDao.update(task)
    }

    companion object {
        private const val DATABASE_NAME = "dinozavrog.db"
    }
}