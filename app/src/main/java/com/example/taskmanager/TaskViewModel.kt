package com.example.taskmanager

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.data.Task
import com.example.taskmanager.data.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.time.LocalDate

class TaskViewModel( private val taskRepository: TaskRepository = Graph.taskRepository ): ViewModel() {
    var taskTitleState by mutableStateOf("")
    var taskDescriptionState by mutableStateOf("")

    var taskDateState by mutableStateOf(LocalDate.now().toString())
    var taskTimeState by mutableStateOf("")

    lateinit var getAllTasks: Flow<List<Task>>
    init {
        viewModelScope.launch {
            getAllTasks = taskRepository.getAllTasks()
        }
    }

    fun getTask(id: Long) : Flow<Task> {
            return taskRepository.getTask(id)
    }

    fun addTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.addTask(task)
        }
    }

    fun update(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.updateTask(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.deleteTask(task)
        }
    }
}