package com.rex.project.helep

import androidx.lifecycle.LiveData
import com.rex.project.helep.local.daos.BiddingDao
import com.rex.project.helep.local.daos.TaskDao
import com.rex.project.helep.local.daos.UserDao
import com.rex.project.helep.local.entities.Bidding
import com.rex.project.helep.local.entities.Task
import com.rex.project.helep.local.entities.TaskAndUser
import com.rex.project.helep.local.entities.User
import com.rex.project.helep.network.ApiInterface
import com.rex.project.helep.utils.Constants

class MainRepository (
    private val userDao: UserDao?,
    private val taskDao: TaskDao?,
    private val biddingDao: BiddingDao?,
    private val apiInterface: ApiInterface
) {
    suspend fun insertUser(user: User) = userDao?.insert(user)

    fun getUserByEmailPassword(email: String, password: String) =
        userDao?.getUserByEmailPassword(email, password)

    suspend fun insertTask(task: Task) = taskDao?.insert(task)

    suspend fun updateTask(task: Task) = taskDao?.update(task)

    fun getPendingTaskByUserId(id: Long) = taskDao?.getPendingTaskWithBiddings(id)

    fun getOnProgressTaskByUserId(id: Long) = taskDao?.getOnProgressTaskWithBiddings(id)

    fun getTaskAndUser(id: Long): LiveData<List<TaskAndUser>>? = taskDao?.getTaskAndUser(id)

    fun getTaskById(id: Long) = taskDao?.getTaskById(id)

    suspend fun updateTaskStatus(id: Long, winnerId: Long, status: String) =
        taskDao?.updateTaskStatus(id, winnerId, status)

    fun getRoute(origin: Map<String, String>, destination: Map<String, String>) =
        apiInterface.findUsers(Constants.API_KEY, origin, destination)

    fun getTaskAndWinnerUser(id: Long) = taskDao?.getTaskAndWinnerUser(id)

    suspend fun insertBidding(bidding: Bidding) = biddingDao?.insert(bidding)
}