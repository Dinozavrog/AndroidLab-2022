package com.example.androidlab_2022.RW

import androidx.recyclerview.widget.DiffUtil
import com.example.androidlab_2022.data.entity.Task


class DifUtils : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean =
        oldItem == newItem

}
