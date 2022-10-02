package com.example.androidlab_2022

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlab_2022.databinding.ActivityMainBinding
import com.example.androidlab_2022.databinding.ActivitySecondBinding


class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }



        intent?.also{
            if(it.action == Intent.ACTION_SEND && it.type=="text/plain"){

                binding.tvFirst.text = intent.getStringExtra(Intent.EXTRA_TEXT)

            }


        }



    }


}