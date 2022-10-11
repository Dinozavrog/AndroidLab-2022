package com.example.androidlab_2022

import android.os.Bundle
import android.provider.SyncStateContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidlab_2022.databinding.FragmentFirstBinding

class FirstFragment : Fragment(R.layout.fragment_first) {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private var counter = 0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFirstBinding.bind(view)
        val bundle = Bundle()

        with(binding) {
            btnClick.setOnClickListener {
                counter++
                tvCount.setText("Counter value: $counter")
                bundle.putString("COUNTER", "counter value: $counter")

            }
            btnSecond.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .setCustomAnimations(
                        android.R.anim.slide_in_left,
                        android.R.anim.slide_out_right
                    )
                    .replace(R.id.container_of_fragments, SecondFragment())
                    .addToBackStack("SecondFragment")
                    .commit()

            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun saveValueToBundle(counterToSave: Int) {
        arguments = Bundle().apply {
            putInt(Digits.COUNT, counterToSave)
        }
    }

    private fun restoreCounterValue() {
        if (arguments != null) {
            counter = arguments?.getInt(Digits.COUNT) ?: 0
        }
    }

    companion object {
        const val FIRST_FRAGMENT_TAG = "FIRST_FRAGMENT_TAG"
        fun getInstance(bundle: Bundle?): FirstFragment {
            val firstFragment = FirstFragment()
            firstFragment.arguments = bundle
            return firstFragment
        }

    }
}