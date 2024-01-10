package com.example.taskmanager.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentTaskBinding
import com.example.taskmanager.model.Task
import com.example.taskmanager.ui.home.HomeFragment

class TaskFragment : Fragment() {
    private lateinit var binding: FragmentTaskBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val editTask = arguments?.getSerializable(HomeFragment.EDIT_TASK_KEY) as Task?
        editTask?.let {
            binding.etTitle.setText(it.title)
            binding.etDesc.setText(it.desc)
            binding.btnSave.text = getString(R.string.update)
        }
        binding.btnSave.setOnClickListener {
            if (editTask == null) {
               save()
            }else {
                update(editTask)
            }
            findNavController().navigateUp()
        }
    }

    private fun update(editTask: Task) {
        App.db.taskDao().update(
            editTask.copy(
                title = binding.etTitle.text.toString(),
                desc = binding.etDesc.text.toString()
            )
        )
    }

    private fun save() {
        val task = Task(
            title = binding.etTitle.text.toString(),
            desc = binding.etDesc.text.toString()
        )
        App.db.taskDao().insert(task)
    }
}