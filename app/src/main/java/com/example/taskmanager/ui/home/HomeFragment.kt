package com.example.taskmanager.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import android.widget.Toast
import androidx.core.os.bundleOf
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentHomeBinding
import com.example.taskmanager.model.Task
import com.example.taskmanager.ui.home.adapter.TaskAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val adapter = TaskAdapter(this::OnLongClick, this::OnClick)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = adapter
        setData()
        fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
    }

   private fun OnLongClick(task: Task) {
        AlertDialog.Builder(requireContext()).apply {
            setTitle("Подтверждение")
            setMessage("Вы уверены, что хотите удалить текст?")
            setPositiveButton(getString(R.string.yes)) { _, _ ->
                val position = adapter.list.indexOf(task)
                adapter.removeTask(position)
                Toast.makeText(requireContext(), "текст удален", Toast.LENGTH_SHORT).show()
            }
            setNegativeButton(getString(R.string.no)) { dialog, _ ->
                dialog.dismiss()
            }
        }.create().show()
    }

    private fun OnClick(task: Task) {
        findNavController().navigate(R.id.taskFragment, bundleOf(EDIT_TASK_KEY to task))
    }

    private fun setData() {
        val tasks = App.db.taskDao().getAll()
        adapter.addTasks(tasks)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val EDIT_TASK_KEY =  "edit.task.key"
    }
}
