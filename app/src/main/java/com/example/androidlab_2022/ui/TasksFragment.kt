package com.example.androidlab_2022.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.androidlab_2022.R
import com.example.androidlab_2022.RW.DifUtils
import com.example.androidlab_2022.RW.TasksListAdapter
import com.example.androidlab_2022.data.TasksRep
import com.example.androidlab_2022.data.entity.Task
import com.example.androidlab_2022.databinding.FragmentTasksBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

class TasksFragment : Fragment(R.layout.fragment_tasks) {

    private var binding: FragmentTasksBinding? = null
    private var adapter: TasksListAdapter? = null
    private var repository: TasksRep? = null
    private val scope = MainScope()
    private var tasks: List<Task>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTasksBinding.bind(view)
        repository = TasksRep(requireContext())

        adapter = TasksListAdapter(
            differ = DifUtils(),
            onDeleteClicked = ::deleteTask,
            onEditClicked = ::EditTaskFragment
        )

        binding?.run {
            fbAdd.setOnClickListener {
                showNewTaskFragment()
            }
            rvTasks.run {
                adapter = adapter
            }
        }
        updateList()

    }

    private fun showNewTaskFragment() {
        parentFragmentManager.beginTransaction()
            .setCustomAnimations(
                androidx.appcompat.R.anim.abc_slide_in_top,
                androidx.appcompat.R.anim.abc_fade_out,
                androidx.appcompat.R.anim.abc_fade_in,
                androidx.appcompat.R.anim.abc_slide_out_top
            )
            .replace(
                R.id.container_of_fragments,
                NewTasksFragment.newInstance(id_task = null),
            )
            .addToBackStack(null)
            .commit()


    }

    private fun EditTaskFragment(id_task: Int) {
        parentFragmentManager.beginTransaction()
            .setCustomAnimations(
                androidx.appcompat.R.anim.abc_slide_in_top,
                androidx.appcompat.R.anim.abc_fade_out,
                androidx.appcompat.R.anim.abc_fade_in,
                androidx.appcompat.R.anim.abc_slide_out_top
            )
            .replace(
                R.id.container_of_fragments,
                NewTasksFragment.newInstance(id_task = id_task),
            )
            .addToBackStack(null)
            .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.delete -> {
                deleteAllTasks()
            }
        }
        return true
    }

    private fun deleteAllTasks() {
        lifecycleScope.launch {
            repository?.deleteAllTasks()
        }
        updateList()
        val message = Snackbar.make(requireActivity().findViewById(R.id.container_of_fragments), "Все заметки удалены", Toast.LENGTH_SHORT)
        message.show()

    }

    private fun deleteTask(id: Int) {
        lifecycleScope.launch {
            repository?.deleteTask(id)
        }
        updateList()
        val message = Snackbar.make(requireActivity().findViewById(R.id.container_of_fragments), "Заметка удалена", Toast.LENGTH_SHORT)
        message.show()
    }



    private fun updateList() {
        lifecycleScope.launch {
            tasks = repository?.getTasks()
        }
        binding?.run {
            if(tasks.isNullOrEmpty()) {
                rvTasks.visibility = View.GONE
                tvNoTasks.setText("Для начала работы создайте первую цель")
            }
            else {
                rvTasks.visibility = View.VISIBLE
                adapter?.submitList(ArrayList(tasks))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        repository = null
        binding = null
        scope.cancel()
    }

    companion object {
        const val TASKS_FRAGMENT_TAG = "TASKS_FRAGMENT_TAG"
        fun newInstance(bundle: Bundle) =
            TasksFragment().apply {
                arguments = Bundle().apply {
                    putAll(bundle)
                }
            }
    }

}