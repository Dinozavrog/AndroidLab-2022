package com.example.androidlab_2022

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlab_2022.databinding.TaroBinding

class TaroHolder (
    val binding: TaroBinding,
    val onItemClicked: (() -> Unit)?,
    val onDeleteClicked: ((Int) -> Unit)?,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        with(binding) {
            root.setOnClickListener {
                onItemClicked?.invoke()
            }
            ibDelete.setOnClickListener {
                onDeleteClicked?.invoke(adapterPosition)
            }
        }
    }

    fun onBind(item: Models.Card) {
        with(binding) {
            tvTitle.text = item.title
            tvDescription.text = item.description
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onItemClicked: (() -> Unit)?,
            onDeleteClicked: ((Int) -> Unit)?
        ): TaroHolder = TaroHolder(
            binding = TaroBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClicked = onItemClicked,
            onDeleteClicked = onDeleteClicked,
        )
    }
}