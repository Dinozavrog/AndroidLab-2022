package com.example.androidlab_2022.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.androidlab_2022.R
import com.example.androidlab_2022.RW.TasksListAdapter
import com.example.androidlab_2022.data.TasksRep
import com.example.androidlab_2022.data.entity.DateToString
import com.example.androidlab_2022.data.entity.Task
import com.example.androidlab_2022.databinding.FragmentNewTaskBinding
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.util.*


class NewTaskFragment : Fragment(R.layout.fragment_new_task) {

    private var binding: FragmentNewTaskBinding? = null
    private var adapter: TasksListAdapter? = null
    private var repository: TasksRep? = null
    private val scope = MainScope()
    private var calendar: Calendar? = null
    private var tasks: List<Task>? = null
    private var currentTaskId: Int? = null
    private lateinit var launcher: ActivityResultLauncher<Array<String>>


//    private val launcher: ActivityResultLauncher<Array<String>> = registerForActivityResult(
//        ActivityResultContracts.RequestMultiplePermissions()
//    ) {
//        when {
//            it.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
//
//            }
//            it.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
//
//            }
//            else -> {}
//        }
////        if (it) {
//////            if((it[Manifest.permission.ACCESS_FINE_LOCATION] == true) and (it[Manifest.permission.ACCESS_COARSE_LOCATION] == true)){
////            Toast.makeText(this.requireContext(), "Вычисляем геопозицию", Toast.LENGTH_LONG)
////                .show()
////        } else {
////            Toast.makeText(this.requireContext(), it.toString(), Toast.LENGTH_LONG).show()
////        }
//    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewTaskBinding.bind(view)
        repository = TasksRep(context = requireContext())
        binding?.run {
            btnBack.setOnClickListener {
                backToListFragment()
            }
            btnChooseDate.setOnClickListener {
//                registerPermissionListener()
//                checkLocationPermission()
                chooseDate()
            }
            btnSend.setOnClickListener {
//                registerPermissionListener()
                checkLocationPermission()
            }
//            addLocation()

        }


    }

    private fun addLocation() {
        TODO("Not yet implemented")
    }


    private fun chooseDate() {
        calendar = Calendar.getInstance()
        val datePickerFragment = DataPickerFragment()
        val supportFragmentManager = requireActivity().supportFragmentManager

        supportFragmentManager.setFragmentResultListener(
            "REQUEST_KEY",
            viewLifecycleOwner
        ) { resultKey, bundle ->
            if (resultKey == "REQUEST_KEY") {
                calendar?.timeInMillis = bundle.getLong("SELECTED_DATE")
                setDate(calendar)
            }
        }
        datePickerFragment.show(supportFragmentManager, "DatePickerDialog")
    }

    private fun setDate(calendar: Calendar?) {
        binding?.apply {
            tvDate.text = calendar?.time?.let {
                getString(R.string.date) + DateToString.convertDateToString(it)
            }
        }
    }

    private fun addTask() {
        lifecycleScope.launch {
            binding?.run {
                repository?.insertTask(
                    Task(
                        null,
                        etTitle.text.toString(),
                        etDesc.text.toString(),
                        calendar?.time,
                        etLongitude.text.toString().toDouble(),
                        etLatitude.text.toString().toDouble()
                    )
                )
            }
        }
        Toast.makeText(this.requireContext(), "ТуДушка добавлена", Toast.LENGTH_LONG).show()
        backToListFragment()
    }

//    private fun checkIfNoteExists() {
//        arguments?.getInt("TASK_ID")?.let {
//            currentTaskId = it
//            setNoteEditingView(it)
//            binding?.toolBar?.title = getString(R.string.edit_note_rus)
//        }
//        if (arguments?.getInt("TASK_ID") == null) {
//            binding?.toolBar?.title = getString(R.string.add_note_rus)
//        }
//    }
//
//    private fun setNoteEditingView(id: Int) {
//        scope.launch {
//            val note = database?.noteDao()?.findById(id)
//            binding?.apply {
//                etTitle.setText(note?.title)
//                etDesc.setText(note?.description)
//                note?.date?.let {
//                    calendar = Calendar.getInstance()
//                    calendar?.time = it
//                    val str = DateToString.convertDateToString(it)
//                    tvDate.text = getString(R.string.date_rus_text) + str
//                }
//            }
//        }
//    }
//
//    private fun saveNote() {
//        if (currentNoteId == null && isNoteCorrect()) {
//            addNote()
//        } else {
//            currentNoteId?.let {
//                updateNote(it)
//            }
//        }
//    }


    private fun backToListFragment() {
        parentFragmentManager.beginTransaction()
            .setCustomAnimations(
                androidx.appcompat.R.anim.abc_slide_in_top,
                androidx.appcompat.R.anim.abc_fade_out,
                androidx.appcompat.R.anim.abc_fade_in,
                androidx.appcompat.R.anim.abc_slide_out_top
            )
            .replace(
                R.id.container_of_fragments,
                TasksFragment(),
            )
            .addToBackStack(null)
            .commit()
    }


//    private fun checkLocationPermission() {
//        Log.e("jfjjfjf", "как же я устала")
//        when {
//            ((ContextCompat.checkSelfPermission(
//                this.requireContext(),
//                Manifest.permission.ACCESS_FINE_LOCATION
//            )
//                    == PackageManager.PERMISSION_GRANTED) and (ContextCompat.checkSelfPermission(
//                this.requireContext(),
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            ) == PackageManager.PERMISSION_GRANTED)) -> {
//                Toast.makeText(this.requireContext(), "camera run", Toast.LENGTH_LONG).show()
//            }
//            shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)-> {
//                Toast.makeText(this.requireContext(), "shouldShowRequestPermissionRationale", Toast.LENGTH_LONG).show()
//            }
//            else -> {
////                launcher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
//                launcher.launch(arrayOf(
//                    Manifest.permission.ACCESS_FINE_LOCATION,
//                    Manifest.permission.ACCESS_COARSE_LOCATION,
//                ))
////                launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
//            }
//        }
//    }

    private fun checkLocationPermission(){
        when{
            (ContextCompat.checkSelfPermission(this.requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) and (ContextCompat.checkSelfPermission(this.requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED)-> {
                Toast.makeText(this.requireContext(), "Camera run", Toast.LENGTH_LONG).show()
            }
            else -> {
                launcher.launch(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION))
            }
        }

    }

    private fun registerPermissionListener() {
//        Toast.makeText(this.requireContext(), "Вычисляем геопозицию", Toast.LENGTH_LONG).show()
        launcher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {

            if((it[Manifest.permission.ACCESS_FINE_LOCATION] == true) and (it[Manifest.permission.ACCESS_COARSE_LOCATION] == true)){
                Toast.makeText(this.requireContext(), "Вычисляем геопозицию", Toast.LENGTH_LONG)
                    .show()
            } else {
                Toast.makeText(this.requireContext(), "иди нахуй", Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        private const val REQUEST_CODE = 1
        const val NEWTASK_FRAGMENT_TAG = "NEWTASK_FRAGMENT_TAG"
        const val TASK_ID = "TASK_ID"
        fun newInstance(id_task: Int?) =
            NewTasksFragment().apply {
                arguments = Bundle().apply {
                    if (id_task != null) {
                        putInt(TASK_ID, id_task)
                    }
                }
            }
    }
}