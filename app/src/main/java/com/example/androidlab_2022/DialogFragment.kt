package ru.kpfu.itis.hometasktwo.fragments

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.androidlab_2022.databinding.FragmentDialogBinding


class DialogFragment(val counterValue: Int, val onButtonClicked: (Int) -> Unit) : DialogFragment() {

    private var _binding: FragmentDialogBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDialogBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val metrics: DisplayMetrics = resources.displayMetrics
        val width: Int = metrics.widthPixels
        dialog?.window?.setLayout(width,
            ViewGroup.LayoutParams.WRAP_CONTENT)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clicks()
    }


    private fun checkValue() : Boolean{
        if (!binding.etCount.text.isNullOrBlank()) {
            val editTextValue = Integer.valueOf(binding.etCount.text.toString())
            if (!(editTextValue in 0..100)) {
                binding.textInputLayout.error = "Неверный формат данных"
                return false
            } else {
                binding.textInputLayout.isErrorEnabled = false
            }
        }
        return true
    }

    private fun clicks(){
        var editTextValue = 0
        with(binding) {
            btnPositive.setOnClickListener {
                if (!binding.etCount.text.isNullOrBlank()) {
                    editTextValue = Integer.valueOf(binding.etCount.text.toString())
                }
                if(checkValue()) {
                    onButtonClicked(counterValue + editTextValue)
                }

            }
            btnNeutral.setOnClickListener {
                dismiss()
            }
            btnNegative.setOnClickListener {
                if (!binding.etCount.text.isNullOrBlank()) {
                    editTextValue = Integer.valueOf(binding.etCount.text.toString())
                }
                if(checkValue()) onButtonClicked(counterValue - editTextValue)

            }
        }
    }

}