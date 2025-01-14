package com.example.taskmanager.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

// CRUD Operations
@Dao
abstract class TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addTask(taskEntity: Task)

    @Update
    abstract suspend fun updateTask(taskEntity: Task)

    @Delete
    abstract suspend fun deleteTask(wishEntity: Task)

    @Query("Select * from `task-table`")
    abstract fun getAllTasks(): Flow<List<Task>>

    @Query("Select * from `task-table` where id=:id")
    abstract fun getTask(id: Long): Flow<Task>
}