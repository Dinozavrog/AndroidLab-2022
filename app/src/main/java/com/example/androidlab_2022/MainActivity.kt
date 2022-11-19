package com.example.androidlab_2022

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidlab_2022.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()  {
    private lateinit var binding: ActivityMainBinding
    private var adapter: TaroListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = TaroListAdapter(
            differ = DifUtils(),
            onItemClicked = ::onItemClicked,
            onDeleteClicked = ::onDeleteClicked
        )
        CardsRep.generateList(20)
        adapter?.submitList(CardsRep.dataList)
        binding.recyclerView.adapter = adapter
        DeleteCard(adapter).attachToRecyclerView(binding.recyclerView)
        AddButton()
    }


    private fun AddButton() {
        binding.fbAddRandomItem.setOnClickListener {
            val dialog = DialogFragment(
                onAddButtonClicked = ::addCustomItem
            )
            dialog.show(supportFragmentManager, "Custom dialog")
        }
    }


    private fun onItemClicked() {

    }

    private fun onDeleteClicked(position: Int) {
        CardsRep.deleteItem(position)
        adapter?.submitList(CardsRep.dataList)
    }

    private fun addCustomItem(position: Int, item: Models.Card) {
        CardsRep.addItem(position, item)
        adapter?.submitList(CardsRep.dataList)
    }
}