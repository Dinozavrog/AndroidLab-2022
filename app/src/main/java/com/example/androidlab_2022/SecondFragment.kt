package com.example.androidlab_2022

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.androidlab_2022.databinding.FragmentFirstBinding
import com.example.androidlab_2022.databinding.FragmentSecondBinding

class SecondFragment : Fragment(R.layout.fragment_second) {
    private var binding: FragmentSecondBinding? = null
    private var c: Int = 5


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSecondBinding.bind(view)
        binding?.run {
            val cards = TaroRepository.cards
            var card_name: String = "a"
            var card_des: String = "a"
            var card_img: String = "a"
            c = arguments?.getInt(CARD_ID)?:-100
            for (i in cards) {
                if (i.id == c) {
                    card_des = i.txt
                    card_name = i.name
                    card_img = i.cover
                }
            }
            tvName.setText("Название-$card_name")
            tvDes.setText("Описание-$card_des")
            Glide.with(this@SecondFragment)
                .load(card_img)
                .into(ivTaro)




        }

        arguments?.getInt("")
    }

    companion object {
        const val DESCRIPTION_FRAGMENT_TAG = "SECOND_FRAGMENT_TAG"
        const val CARD_ID = "CARD_ID"
        fun newInstance(id_card : Int) =
            SecondFragment().apply {
                arguments = Bundle().apply {
                    putInt(CARD_ID, id_card)
                }
            }
    }

}