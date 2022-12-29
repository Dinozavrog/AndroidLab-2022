package com.example.androidlab_2022

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.androidlab_2022.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    private val settings =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

        }

    private val launcher =
        registerForActivityResult(MyActivityResultContract()) { pic ->
        binding?.ivPic?.setImageBitmap(pic)
    }
    private val permission =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { isGranted ->
            isGranted?.run {
                when {
                    isGranted.values.all { true } -> {
                        launcher.launch(null)
                    }
                    !shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) or !shouldShowRequestPermissionRationale(
                        Manifest.permission.READ_EXTERNAL_STORAGE) or !shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                     ->
                    {
                        showDialog()
                    }
                    else -> showSecondDialog()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.run {
            btnPic.setOnClickListener {
                if (shouldShowRequestPermissionRationale(
                        Manifest.permission.CAMERA
                    ) or shouldShowRequestPermissionRationale(
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                ) {
                    all_permissions()
                } else {
                    permission.launch(
                        arrayOf(
                            Manifest.permission.CAMERA,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        )
                    )
                }
            }
        }
    }

    private fun all_permissions() {
        val permissions = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
        )
        this.requestPermissions(permissions, 18)
    }


    private fun appSettings() {
        val appSettingsIntent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.parse("package:" + this.packageName)
        )
        settings.launch(appSettingsIntent)
    }


    private fun showDialog() {
        AlertDialog.Builder(applicationContext)
            .setTitle("Даете ли вы разрешение на камеру?")
            .setMessage("message")
            .setPositiveButton("Yes") { dialog, _ ->
                appSettings()
                dialog.dismiss()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun showSecondDialog() {
        AlertDialog.Builder(applicationContext)
            .setTitle("Даете ли вы разрешение на камеру?")
            .setMessage("message")
            .setPositiveButton("Yes") { dialog, _ ->
                all_permissions()
                dialog.dismiss()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

}