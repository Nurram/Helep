package com.rex.project.helep

import androidx.lifecycle.LiveData
import com.rex.project.helep.local.daos.TaskDao
import com.rex.project.helep.local.daos.UserDao
import com.rex.project.helep.local.daos.WalletDao
import com.rex.project.helep.local.entities.*
import com.rex.project.helep.network.ApiInterface
import com.rex.project.helep.utils.Constants

class MainRepository (
    private val userDao: UserDao?,
    private val taskDao: TaskDao?,
    private val apiInterface: ApiInterface,
    private val walletDao: WalletDao?
) {
    fun insertUser(user: User) = userDao?.insert(user)

    fun getUserByEmailPassword(email: String, password: String) =
        userDao?.getUserByEmailPassword(email, password)

    fun insertTask(task: Task) = taskDao?.insert(task)

    fun getPendingTaskByUserId(id: Long) = taskDao?.getPendingTask(id)

    fun getOnProgressTaskByUserId(id: Long) = taskDao?.getOnProgressTaskByUserId(id)

    fun getTaskAndUser(id: Long): LiveData<List<TaskAndUser>>? = taskDao?.getTaskAndUser(id)

    fun getTaskById(id: Long) = taskDao?.getTaskById(id)

    fun updateTaskStatus(id: Long, winnerId: Long, status: String) =
        taskDao?.updateTaskStatus(id, winnerId, status)

    fun getRoute(origin: Map<String, String>, destination: Map<String, String>) =
        apiInterface.findUsers(Constants.API_KEY, origin, destination)

    fun insertWallet(wallet: Wallet) = walletDao?.insertWallet(wallet)

    fun getWalletByUserId(userId: Long) = walletDao?.getWalletByUserId(userId)

    fun topUpWallet(userId: Long, value: Long) = walletDao?.topUpWallet(userId, value)

    fun spendWallet(userId: Long, value: Long) = walletDao?.spendWallet(userId, value)
}