package com.example.androidlab_2022

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import com.example.androidlab_2022.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    private val score = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        super.onCreate(savedInstanceState)

        binding?.run {
            btnGoogle.setOnClickListener {
                sendImplictIntentIntent()
            }
            btnPhone.setOnClickListener {
                sendContact()
            }
            btnEmail.setOnClickListener {
                sendEmail()
            }
        }

    }

    private fun sendImplictIntentIntent() {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("geo:47.6,-122.3?z=11")
        }
        val chooserIntent = Intent.createChooser(
            intent,
            "hi "
        )
        if (chooserIntent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun sendContact() {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$89172872567")
        }
        val chooserIntent = Intent.createChooser(
            intent,
            "hi "
        )
        if (chooserIntent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun sendEmail() {
        val recipients = arrayOf("zaripovadin@yandex.ru")
        val subject = "Dinozavr"
        val content = "Hi, dinozavr!"
        var intentEmail: Intent = Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
        intentEmail.putExtra(Intent.EXTRA_EMAIL, recipients);
        intentEmail.putExtra(Intent.EXTRA_SUBJECT, subject);
        intentEmail.putExtra(Intent.EXTRA_TEXT, content);
        intentEmail.setType("text/plain");

        startActivity(Intent.createChooser(intentEmail, "Choose an email client from..."));
    }
}