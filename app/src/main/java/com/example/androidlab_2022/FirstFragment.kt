package com.example.androidlab_2022

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.androidlab_2022.databinding.FragmentFirstBinding
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter


class FirstFragment : Fragment(R.layout.fragment_first) {

    private var binding: FragmentFirstBinding? = null

    private var adapter: TaroAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFirstBinding.bind(view)

        binding?.run {
            adapter = TaroAdapter(TaroRepository.cards) {
                click(it.id)
            }
            rvTaro.adapter = adapter
            rvTaro.adapter = ScaleInAnimationAdapter(adapter!!).apply {
                setDuration(1000)
                // Change the interpolator.

                // Disable the first scroll mode.
                setFirstOnly(false)

            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun click(id_card: Int) {



        parentFragmentManager.beginTransaction()
            .setCustomAnimations(
                androidx.appcompat.R.anim.abc_slide_in_top,
                androidx.appcompat.R.anim.abc_fade_out,
                androidx.appcompat.R.anim.abc_fade_in,
                androidx.appcompat.R.anim.abc_slide_out_top
            )
            .replace(
                R.id.container_of_fragments,
                SecondFragment.newInstance(id_card = id_card),
            )
            .addToBackStack(null)
            .commit()
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