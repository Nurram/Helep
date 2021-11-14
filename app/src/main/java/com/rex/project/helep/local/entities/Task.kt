package com.rex.project.helep.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val shortDesc: String,
    val category: String,
    val tasks: Map<String, Any>,
    val price: Long
)