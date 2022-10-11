package com.example.androidlab_2022

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.androidlab_2022.databinding.FragmentFirstBinding
import ru.kpfu.itis.hometasktwo.fragments.DialogFragment

class FirstFragment : Fragment(R.layout.fragment_first) {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private var counter = 0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFirstBinding.bind(view)


        with(binding) {
            btnClick.setOnClickListener {
                counter++
                tvCount.setText("Counter value: $counter")

            }
            btnSecond.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .setCustomAnimations(
                        android.R.anim.slide_in_left,
                        android.R.anim.slide_out_right
                    )
                    .replace(R.id.container_of_fragments, SecondFragment.newInstance("$counter"))
                    .addToBackStack("SecondFragment")
                    .commit()

            }
            btnDialog.setOnClickListener {
                val dialog = DialogFragment(counterValue = counter,
                    { counterDialog ->
                        counter = counterDialog
                        tvCount.setText("Counter value: $counter")
                    }

                )
                dialog.show(parentFragmentManager, "Custom dialog")
            }



        }


    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




    companion object {
        const val FIRST_FRAGMENT_TAG = "FIRST_FRAGMENT_TAG"
        fun getInstance(bundle: Bundle?): FirstFragment {
            val firstFragment = FirstFragment()
            firstFragment.arguments = bundle
            return firstFragment
        }
        fun newInstance(name: String) = FirstFragment().apply {
            arguments = Bundle().apply {
                putString("COUNT", name)
            }
        }

    }
}