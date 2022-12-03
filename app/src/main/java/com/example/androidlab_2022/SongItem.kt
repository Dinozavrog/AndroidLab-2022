package com.example.androidlab_2022

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidlab_2022.databinding.ItemSongBinding
import com.google.android.material.internal.ContextUtils.getActivity

class SongItem(
    private val binding: ItemSongBinding,
//    private val action: (Song) -> Unit,
//    private var binder: HelloService.HelloBinder? = null,


) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(song: Song) {
        with(binding) {
            tvName.text = song.name
            tvDes.text = song.author

            Glide.with(binding.root)
                .load(song.cover)
                .into(ivCover)

//            root.setOnClickListener {
//                action(song)
//            }
        }
    }

}

