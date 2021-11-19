package com.rex.project.helep.local.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rex.project.helep.local.entities.Wallet

@Dao
interface WalletDao {

    @Insert
    fun insertWallet(wallet: Wallet)

    @Query("SELECT * FROM wallet")
    fun getAllWallet(): LiveData<List<Wallet>>

    @Query("SELECT * FROM wallet WHERE userId=:userId")
    fun getWalletByUserId(userId: Long): LiveData<Wallet>

    @Query("UPDATE wallet SET balance=balance+:value, credit=credit+:value WHERE userId=:userId")
    fun topUpWallet(userId: Long, value: Long)

    @Query("UPDATE wallet SET balance=balance-:value, credit=credit+:value WHERE userId=:userId")
    fun spendWallet(userId: Long, value: Long)
}