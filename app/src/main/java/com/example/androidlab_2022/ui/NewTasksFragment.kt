package com.example.androidlab_2022.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.androidlab_2022.R
import com.example.androidlab_2022.RW.TasksListAdapter
import com.example.androidlab_2022.data.TasksRep
import com.example.androidlab_2022.data.entity.DateToString
import com.example.androidlab_2022.data.entity.Task
import com.example.androidlab_2022.databinding.FragmentNewTaskBinding
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.util.*


class NewTasksFragment : Fragment(R.layout.fragment_new_task) {

    private var binding: FragmentNewTaskBinding? = null
    private var adapter: TasksListAdapter? = null
    private var repository: TasksRep? = null
    private val scope = MainScope()
    private var client: FusedLocationProviderClient? = null
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


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        registerPermissionListener()
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
//                checkLocationPermission()
                saveTask()
            }
//            addLocation()

        }
        TaskExists()
//        setLocation()
        checkPermission()


    }

    private fun TaskExists() {
        arguments?.getInt(TASK_ID)?.let {
            currentTaskId = it
            setTaskEditFragment(it)
        }
        if (arguments?.getInt(TASK_ID) == 0) {
            Log.e("jfjjf", "Добавление заметки")
            Toast.makeText(this.requireContext(), "Добавление новой заметки", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setTaskEditFragment(id: Int) {
        lifecycleScope.launch {
            val task = repository?.findTask(id)
            binding?.run {
                etTitle.setText(task?.title)
                etDesc.setText(task?.description)
                task?.date?.let {
                    calendar = Calendar.getInstance()
                    calendar?.time = it
                    val str_date = DateToString.convertDateToString(it)
                    tvDate.text = getString(R.string.date) + str_date
                }
            }
        }
    }

    private fun saveTask() {
        if (currentTaskId == 0 && isTaskCorrect()) {
            addTask()
        } else {
            currentTaskId?.let {
                updateTask(it)
            }
        }
    }

    private fun addTask() {
        lifecycleScope.launch {
            binding?.apply {
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
        Log.e("fkkfkf", "SAVE")
        Toast.makeText(this.requireContext(), "Заметка сохранена", Toast.LENGTH_SHORT).show()
        backToListFragment()
    }

    private fun updateTask(id: Int) {
        lifecycleScope.launch {
            val task = repository?.findTask(id)
            binding?.apply {
                if (isTaskCorrect()) {
                    binding?.run {
                        task?.let { task ->
                            task.title = etTitle.text.toString()
                            task.description = etDesc.text.toString()
                            calendar?.also {
                                task.date = it.time
                            }
                            repository?.updateTask(task)
                            Toast.makeText(requireContext(), "Заметка изменена", Toast.LENGTH_SHORT).show()
                            backToListFragment()
                        }
                    }
                }
            }
        }
    }

    private fun isTaskCorrect(): Boolean {
        binding?.run {
            if (etTitle.text.toString().isEmpty()) {
                Toast.makeText(requireContext(), "Добавьте название", Toast.LENGTH_SHORT).show()
                return false
            }
            if (etDesc.text.toString().isEmpty()) {
                Toast.makeText(requireContext(), "Добавьте описание", Toast.LENGTH_SHORT).show()
                return false
            }
        }
        return true
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


//    private fun checkLocation1Permission() {
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
//
//    private fun checkLocationPermission(){
//        when{
//            ((ContextCompat.checkSelfPermission(this.requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
//                    == PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(this.requireContext(),
//            Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)) ->{
//                        Toast.makeText(this.requireContext(), "Сфигали ты ран то",
//                        Toast.LENGTH_LONG).show()
//                    }
//            else->{
//                launcher.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION))
//            }
//        }
//    }
//
//    private fun registerPermissionListener(){
//        launcher = registerForActivityResult(
//            ActivityResultContracts.RequestMultiplePermissions()
//        ){
//            if((it[Manifest.permission.ACCESS_COARSE_LOCATION] == true)
//            and (it[Manifest.permission.ACCESS_FINE_LOCATION] == true)){
//                Toast.makeText(this.requireContext(), it[Manifest.permission.ACCESS_FINE_LOCATION].toString(), Toast.LENGTH_LONG).show()
//            }
//            else{
//                Toast.makeText(this.requireContext(), "Permission denied", Toast.LENGTH_LONG).show()
//            }
//        }
//    }
    private fun setLocation() {
        if (checkPermissions() == true) {
            getCurrentLocation()
        } else {
            requestPermissions(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ), REQUEST_CODE
            )
        }
    }

        private fun checkPermissions(): Boolean? {
            activity?.apply {
                return (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
                        == PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(
                            this,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        )
                        == PackageManager.PERMISSION_GRANTED)
            }
            return null
        }

        override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
        ) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)

            if (requestCode == REQUEST_CODE && grantResults.isNotEmpty() &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                getCurrentLocation()
            } else {
                Log.e("что за хуйня", "hui")
                backToListFragment()
            }
        }

        @SuppressLint("MissingPermission")
        private fun getCurrentLocation() {
            val locationManager =
                activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) or
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
            ) {
                if (checkPermissions() == true) {
                    client?.lastLocation?.addOnCompleteListener {
                        val location = it.result
                        if (location != null) {
                            binding?.etLongitude?.setText(location.longitude.toString())
                            binding?.etLatitude?.setText(location.latitude.toString())
                        }
                    }
                }
            } else {
                startActivity(
                    Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                )
            }
        }

    private fun checkPermission(){
        if (checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION,
            ) == PackageManager.PERMISSION_GRANTED){
            getCurrentLocation()
        }else{
            permissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }
    @SuppressLint("MissingPermission")
    private val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            if (
                it[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                it[Manifest.permission.ACCESS_COARSE_LOCATION] == true
            ) {
                getCurrentLocation()
            }else{
                Log.e("пошла я нахуй", "идите нахуй")
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