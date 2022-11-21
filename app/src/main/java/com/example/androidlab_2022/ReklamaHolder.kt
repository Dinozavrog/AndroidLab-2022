package com.example.androidlab_2022

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlab_2022.databinding.ReklamaBinding
import coil.load
import com.bumptech.glide.Glide


class ReklamaHolder(val binding: ReklamaBinding)  :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: Models.Rekalama) {
        with(binding) {
            Glide.with(binding.root)
                .load(item.imageUrl)
                .into(ivAd)
            tvTitle.text = item.title
        }
    }

    companion object {
        fun create(parent: ViewGroup): ReklamaHolder = ReklamaHolder(
            ReklamaBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        )
    }
}