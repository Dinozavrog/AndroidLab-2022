package com.example.androidlab_2022

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidlab_2022.databinding.FragmentSecondBinding


class SecondFragment : Fragment(R.layout.fragment_second) {
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        work_please_work()


    }


    private fun work_please_work() {
        val counter_val = arguments?.getString("COUNT")


        with(binding) {
            btnBack.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .replace(R.id.container_of_fragments, FirstFragment())
                    .commit()

            }

            tvSecondcount.setText("Counter value: $counter_val")

            when (counter_val?.toInt()) {
                in 0..50 -> {
                    scrSecond.setBackgroundColor(Color.parseColor("#CA517A"))
                }
                in 51..100 -> {
                    scrSecond.setBackgroundColor(Color.parseColor("#5E5555"))
                }
                in 100..Integer.MAX_VALUE -> {
                    scrSecond.setBackgroundColor(Color.parseColor("#A93FBC"))
                }
            }

        }




    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {

        fun newInstance(name: String) = SecondFragment().apply {
            arguments = Bundle().apply {
                putString("COUNT", name)
            }
        }

    }


    }



