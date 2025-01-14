package com.example.taskmanager.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task-table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "task-title")
    val taskTitle: String = "",

    @ColumnInfo(name = "task-description")
    val taskDescription: String?,

    @ColumnInfo(name = "task-date")
    val taskDate: String,

    @ColumnInfo(name = "task-time")
    val taskTime: String?,

    @ColumnInfo(name = "task-lastModifiedDateTime")
    val lastModifiedDateTime: String = ""
)
