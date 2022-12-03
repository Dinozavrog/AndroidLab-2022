package com.example.androidlab_2022

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import com.example.androidlab_2022.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        startService(Intent(this, HelloService::class.java).apply {
//            putExtra("MEDIA_ACTION", MediaActions.PLAY as Parcelable)
//        })
//        if(savedInstanceState !=null) {
//            return
//        }
        supportFragmentManager.beginTransaction()
            .add(
                R.id.container_of_fragments,
                FirstFragment.newInstance(Bundle()),
                FirstFragment.FIRST_FRAGMENT_TAG
            ).commit()
    }
}