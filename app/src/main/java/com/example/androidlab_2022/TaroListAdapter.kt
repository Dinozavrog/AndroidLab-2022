package com.example.androidlab_2022

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class TaroListAdapter (
    val differ: DiffUtil.ItemCallback<Models>,
    val onItemClicked: (()-> Unit)?,
    val onDeleteClicked: ((Int)-> Unit)
) :
    ListAdapter<Models, RecyclerView.ViewHolder>(differ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            R.layout.reklama -> ReklamaHolder.create(parent)
            R.layout.taro -> TaroHolder.create(parent, onItemClicked, onDeleteClicked)
            else -> throw IllegalArgumentException("There is no viewHolder for such an item! : )")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = currentList[position]
        when (holder) {
            is ReklamaHolder -> holder.onBind(item as Models.Rekalama)
            is TaroHolder -> holder.onBind(item as Models.Card)
        }
    }

    override fun getItemViewType(position: Int): Int =
        when (currentList[position]) {
            is Models.Card -> R.layout.taro
            is Models.Rekalama -> R.layout.reklama
        }

}
