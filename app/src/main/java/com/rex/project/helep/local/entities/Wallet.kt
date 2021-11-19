package com.rex.project.helep.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wallet")
data class Wallet(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val userId: Long,
    val balance: Long = 0,
    val debit: Long = 0,
    val credit: Long = 0
)