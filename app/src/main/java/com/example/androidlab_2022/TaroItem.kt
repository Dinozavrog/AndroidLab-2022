package com.example.androidlab_2022

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidlab_2022.databinding.ItemTaroBinding

class TaroItem(
    private val binding: ItemTaroBinding,
    private val action: (Taro) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(taro: Taro) {
        with(binding) {
            tvTitle.text = taro.name
            tvDescrtiption.text = taro.description

            Glide.with(binding.root)
                .load(taro.cover)
                .into(ivCard)

            root.setOnClickListener {
                action(taro)
            }

        }
    }
}