package com.rex.project.helep.local.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rex.project.helep.local.entities.Task
import com.rex.project.helep.local.entities.TaskAndUser
import com.rex.project.helep.local.entities.TaskAndUserWinner
import com.rex.project.helep.utils.Constants

@Dao
interface TaskDao {
    @Insert
    fun insert(task: Task)

    @Transaction
    @Query("SELECT * FROM task WHERE NOT userId=:id")
    fun getTaskAndUser(id: Long): LiveData<List<TaskAndUser>>

    @Transaction
    @Query("SELECT * FROM task WHERE id=:id")
    fun getTaskById(id: Long): LiveData<Task>

    @Transaction
    @Query("SELECT * FROM task WHERE userId=:id AND status=:status")
    fun getPendingTask(
        id: Long,
        status: String = Constants.PENDING
    ): LiveData<List<Task>>

    @Transaction
    @Query("SELECT * FROM task WHERE userId=:id AND status=:status")
    fun getOnProgressTaskByUserId(
        id: Long,
        status: String = Constants.ON_PROGRESS
    ): LiveData<List<Task>>

    @Query("UPDATE task SET status=:status, winnerId=:winnerId WHERE id=:id")
    fun updateTaskStatus(id: Long, winnerId: Long, status: String)

    @Query("SELECT * FROM task WHERE id=:id")
    fun getTaskAndWinnerUser(id: Long): LiveData<TaskAndUserWinner>
}