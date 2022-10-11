package com.example.androidlab_2022

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidlab_2022.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val container: Int = R.id.container_of_fragments
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState != null) {
            return
        }

        supportFragmentManager.beginTransaction().add(
            container,
            FirstFragment.getInstance(Bundle()),
            FirstFragment.FIRST_FRAGMENT_TAG
        ).setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out)
            .addToBackStack(null)
            .commit()
    }
}