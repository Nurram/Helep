package com.rex.project.helep.local.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.rex.project.helep.utils.Constants
import kotlinx.parcelize.Parcelize
import java.util.*

@Entity(
    tableName = "task",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = CASCADE)])
@Parcelize
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val shortDesc: String,
    val category: String,
    val taskOne: String,
    val taskOneCount: Int,
    val taskTwo: String,
    val taskTwoCount: Int,
    val price: Long,
    val userId: Long,
    var status: String = Constants.PENDING,
    var winnerId: Long = -1L
): Parcelable