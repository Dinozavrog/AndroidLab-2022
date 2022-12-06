package com.example.androidlab_2022

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlab_2022.databinding.ItemSongBinding

class SongAdapter(
    private val list: List<Song>,
    private val action: (Song) -> Unit
) : RecyclerView.Adapter<SongItem>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): SongItem = SongItem(ItemSongBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
        ),
        action
    )

    override fun onBindViewHolder(
        holder: SongItem,
        position: Int) {

        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}