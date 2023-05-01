package com.example.androidlab_2022

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlab_2022.databinding.ItemTaroBinding

class TaroAdapter(
    private val list: List<Taro>,
    private val action: (Taro) -> Unit
) : RecyclerView.Adapter<TaroItem>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): TaroItem = TaroItem(ItemTaroBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
        ),
        action
    )

    override fun onBindViewHolder(
        holder: TaroItem,
        position: Int
    ) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}