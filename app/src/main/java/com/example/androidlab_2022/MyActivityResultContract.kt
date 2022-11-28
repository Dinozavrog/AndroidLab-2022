package com.example.androidlab_2022

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContract

class MyActivityResultContract : ActivityResultContract<Uri?, Bitmap?>() {

    private var context: Context? = null

    override fun createIntent(context: Context, input: Uri?): Intent {
        this.context = context
        val galleryIntent = Intent(Intent.ACTION_PICK).apply {
            type = "image/*"
        }
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val chooserIntent = Intent.createChooser(galleryIntent, context.getString(R.string.choose))
        chooserIntent?.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(cameraIntent))
        return chooserIntent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Bitmap? {
        intent.takeIf { resultCode == Activity.RESULT_OK }?.data?.run {
            val pic = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                context?.contentResolver?.let {
                    val source = ImageDecoder.createSource(it, this)
                    ImageDecoder.decodeBitmap(source)
                }
            } else {
                MediaStore.Images.Media.getBitmap(
                    context?.contentResolver,
                    this
                )
            }
            return pic
        }
        return intent.takeIf { resultCode == Activity.RESULT_OK }?.getParcelableExtra("data")
    }
}