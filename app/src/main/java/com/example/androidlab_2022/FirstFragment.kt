package com.example.androidlab_2022

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.androidlab_2022.databinding.FragmentFirstBinding



class FirstFragment : Fragment(R.layout.fragment_first) {

    private var binding: FragmentFirstBinding? = null
    private var adapter: SongAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFirstBinding.bind(view)

        binding?.run {
            adapter = SongAdapter(SongRep.songs)
            {
                song(it.id)
            }
            rvMusic.adapter = adapter
        }
    }

    private fun song(id_song: Int) {
        parentFragmentManager.beginTransaction()
            .setCustomAnimations(
                androidx.appcompat.R.anim.abc_slide_in_top,
                androidx.appcompat.R.anim.abc_fade_out,
                androidx.appcompat.R.anim.abc_fade_in,
                androidx.appcompat.R.anim.abc_slide_out_top
            )
            .replace(
                R.id.container_of_fragments,
                SongFragment.newInstance(id_song = id_song),
            )
            .addToBackStack(null)
            .commit()

    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        const val FIRST_FRAGMENT_TAG = "FIRST_FRAGMENT_TAG"
        fun newInstance(bundle: Bundle) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putAll(bundle)
                }
            }
    }
}