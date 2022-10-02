package com.example.androidlab_2022

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.androidlab_2022.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)


        setContentView(binding.root)
        val Nice_button = binding.editBtn
        val Nice_image = binding.tvDescription

        var flag = false
        Nice_button.setOnClickListener {
            if (!flag) {
                Nice_image.visibility = View.GONE
            } else {
                Nice_image.visibility = View.VISIBLE
            }
            flag = !flag
        }
    }
}