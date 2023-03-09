package com.example.androidlab_2022.RW

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlab_2022.data.entity.Task
import com.example.androidlab_2022.databinding.TaskBinding

class TasksHolder (
    val binding: TaskBinding,
    val onDeleteClicked: ((Int) -> Unit),
    val onEditClicked: ((Int) -> Unit),
    private var task: Task? = null
        ) : RecyclerView.ViewHolder(binding.root) {



    fun bind(item: Task) {
        this.task = item
        with(binding) {
            tvTitle.text = item.title
            tvDescription.text = item.description
            tvDate.text = item.date.toString()
            tvLocation.text = item.longitude.toString() + " " + item.latitude.toString()

            ibEdit.setOnClickListener {
                item.id?.let { it1 -> onEditClicked(it1) }
            }
            ibDelete.setOnClickListener {
                item.id?.let { it1 -> onDeleteClicked(it1) }
            }
        }
    }


    companion object {
        fun create(
            parent: ViewGroup,
            onDeleteClicked: ((Int) -> Unit),
            onEditClicked: ((Int) -> Unit),
        ): TasksHolder = TasksHolder(
            binding = TaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onDeleteClicked,
            onEditClicked,
        )
    }
}