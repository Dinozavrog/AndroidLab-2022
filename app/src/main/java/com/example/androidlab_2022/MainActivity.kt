package com.example.androidlab_2022

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidlab_2022.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    private val score = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}