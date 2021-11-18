package com.rex.project.helep.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bidding")
data class Bidding(
    @PrimaryKey
    val id: Long,
    val userId: Long,
    val taskId: Long,
    val priceBid: Long,
)