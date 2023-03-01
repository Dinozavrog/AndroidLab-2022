package com.example.androidlab_2022

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidlab_2022.databinding.FragmentLessonsBinding

class LessonsFragment : Fragment(R.layout.fragment_lessons) {
    private var binding: FragmentLessonsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLessonsBinding.bind(view)}

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
