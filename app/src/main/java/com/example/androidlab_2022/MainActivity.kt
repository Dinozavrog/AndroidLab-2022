package com.example.androidlab_2022

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import com.example.androidlab_2022.R
import com.example.androidlab_2022.data.AppDatabase
import com.example.androidlab_2022.data.TasksRep
import com.example.androidlab_2022.data.entity.Task
import com.example.androidlab_2022.databinding.ActivityMainBinding
import com.example.androidlab_2022.ui.TasksFragment
import com.facebook.stetho.Stetho
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var counter: Int = 0
    private var repository: TasksRep? = null
    private var tasks: List<Task>? = null
    private var database: AppDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Stetho.initializeWithDefaults(this)
        OkHttpClient.Builder()
        setContentView(R.layout.activity_main)
//        supportActionBar?.title = "ToDo List"
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState != null) {
            return
        }
        supportFragmentManager.beginTransaction()
            .add(
                R.id.container_of_fragments,
                TasksFragment.newInstance(Bundle()),
                TasksFragment.TASKS_FRAGMENT_TAG
            ).commit()


    }

}