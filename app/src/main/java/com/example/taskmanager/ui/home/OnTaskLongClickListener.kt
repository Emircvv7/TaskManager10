package com.example.taskmanager.ui.home

import com.example.taskmanager.model.Task

interface OnTaskLongClickListener {
    fun onTaskLongClick(task: Task, position: Int)
}