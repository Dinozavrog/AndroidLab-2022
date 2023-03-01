package com.example.androidlab_2022

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.androidlab_2022.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home){
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val options = navOptions {

            launchSingleTop = false
            popUpTo(R.id.homeFragment)
            anim {
                enter = android.R.anim.slide_in_left
                exit = android.R.anim.slide_out_right
                popEnter = android.R.anim.slide_in_left
                popExit = android.R.anim.slide_out_right
            }
        }
        binding.run {
            btnFirst.setOnClickListener {
                findNavController().navigate(
                    R.id.action_homeFragment_to_firstFragment,
                    null,
                    options
                )
            }
            btnSecond.setOnClickListener {
                findNavController().navigate(
                    R.id.action_homeFragment_to_secondFragment,
                    null,
                    options
                )
            }
            btnThird.setOnClickListener {
                findNavController().navigate(
                    R.id.action_homeFragment_to_thirdFragment,
                    null,
                    options
                )
            }
            btnFourth.setOnClickListener {
                findNavController().navigate(
                    R.id.action_homeFragment_to_fourthFragment,
                    null,
                    options
                )
            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}