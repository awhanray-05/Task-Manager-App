package com.example.taskmanager

import android.content.Context
import androidx.room.Room
import com.example.taskmanager.data.TaskDatabase
import com.example.taskmanager.data.TaskRepository

object Graph {
    lateinit var database: TaskDatabase

    val taskRepository by lazy {
        TaskRepository(taskDao = database.taskDao())
    }

    fun provide(context: Context) {
        database = Room.databaseBuilder(context, TaskDatabase::class.java, "tasklist.db").build()
    }
}