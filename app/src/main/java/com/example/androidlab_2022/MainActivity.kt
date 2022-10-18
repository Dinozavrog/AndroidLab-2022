package com.example.androidlab_2022

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidlab_2022.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState !=null) {
            return
        }
        supportFragmentManager.beginTransaction()
            .add(
                R.id.container_of_fragments,
                FirstFragment.newInstance(Bundle()),
                FirstFragment.FIRST_FRAGMENT_TAG
            ).commit()
    }

    }
