package com.example.androidlab_2022

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class DeleteCard (adapter: TaroListAdapter?) :

    ItemTouchHelper(object : SimpleCallback(0, ItemTouchHelper.RIGHT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = false


        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            CardsRep.deleteItem(viewHolder.adapterPosition)
            adapter?.submitList(CardsRep.dataList)
        }
    })