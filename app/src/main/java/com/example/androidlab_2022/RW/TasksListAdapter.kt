package com.example.androidlab_2022.RW

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlab_2022.R
import com.example.androidlab_2022.data.entity.Task

class TasksListAdapter (
    val differ: DiffUtil.ItemCallback<Task>,
    val onDeleteClicked: ((Int)-> Unit),
    val onEditClicked: ((Int)-> Unit),
):
        ListAdapter<Task, RecyclerView.ViewHolder>(differ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            R.layout.task -> TasksHolder.create(parent, onDeleteClicked, onEditClicked)
            else -> throw IllegalArgumentException("шото не так пошло ребзя")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = currentList[position]
        when (holder) {
            is TasksHolder -> holder.bind(item as Task)
        }
    }




    override fun getItemViewType(position: Int): Int =
        when (currentList[position]) {
            is Task -> R.layout.task
            else -> Log.e("eeee", "eee")
        }
    }


