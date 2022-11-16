package com.example.androidlab_2022

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.DialogFragment
import android.view.ViewGroup
import com.example.androidlab_2022.databinding.FragmentDialogBinding


class DialogFragment(
    val onAddButtonClicked: (position: Int, Models.Card) -> Unit
) :
    DialogFragment() {

    private var _binding: FragmentDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDialogBinding.inflate(layoutInflater)
        return (binding.root)
    }

    override fun onStart() {
        super.onStart()
        val metrics: DisplayMetrics = resources.displayMetrics
        val width: Int = metrics.widthPixels
        dialog?.window?.setLayout(
            width,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initValues()
    }

    private fun initValues() {
        with(binding) {
            btnSubmit.setOnClickListener {
                val description = etDescription.text.toString()
                val title = etTitle.text.toString()
                val position = if (etTitle.text.toString().isNotBlank())
                    Integer.valueOf(etPosition.text.toString()) else CardsRep.dataList.size
                onAddButtonClicked(position, Models.Card(description, title))
                dialog?.hide()
            }
        }
    }
}
