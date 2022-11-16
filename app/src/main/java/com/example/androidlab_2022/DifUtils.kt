package com.example.androidlab_2022

import androidx.recyclerview.widget.DiffUtil

class DifUtils : DiffUtil.ItemCallback<Models>() {
    override fun areItemsTheSame(oldItem: Models, newItem: Models): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Models, newItem: Models): Boolean =
        oldItem == newItem

}