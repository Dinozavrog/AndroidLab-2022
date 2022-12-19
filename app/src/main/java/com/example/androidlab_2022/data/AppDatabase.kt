package com.example.androidlab_2022.data

import android.content.Context
import androidx.room.*
import com.example.androidlab_2022.data.dao.TaskDao
import com.example.androidlab_2022.data.entity.ConvertDate
import com.example.androidlab_2022.data.entity.Task

@Database(entities = [Task::class], version = 2)
@TypeConverters(ConvertDate::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getTaskDao(): TaskDao

//    private fun buildDatabase(context: Context) =
//        Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
//            .fallbackToDestructiveMigration()
//            .build()
//}
}