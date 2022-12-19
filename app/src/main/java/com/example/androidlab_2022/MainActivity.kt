package com.example.androidlab_2022

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import com.example.androidlab_2022.R
import com.example.androidlab_2022.databinding.ActivityMainBinding
import com.example.androidlab_2022.ui.TasksFragment
import com.facebook.stetho.Stetho
import okhttp3.OkHttpClient

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var counter: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Stetho.initializeWithDefaults(this)
        OkHttpClient.Builder()
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "ToDo List"
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState !=null) {
            return
        }
        supportFragmentManager.beginTransaction()
            .add(
                R.id.container_of_fragments,
                TasksFragment.newInstance(Bundle()),
                TasksFragment.TASKS_FRAGMENT_TAG
            ).commit()


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
//            R.id.delete -> {
//                Toast.makeText(this, "Все заметки удалены", Toast.LENGTH_SHORT).show()
//            }
            R.id.change -> {
                change_theme()
                if(counter % 2 == 0) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        }
        return true

    }

    fun change_theme(){
        counter++
    }
}