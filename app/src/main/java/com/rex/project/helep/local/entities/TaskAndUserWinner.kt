package com.rex.project.helep.local.entities

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.parcelize.Parcelize

@Parcelize
data class TaskAndUserWinner(
    @Embedded
    val task: Task,
    @Relation(
        parentColumn = "winnerId",
        entityColumn = "id"
    )
    val user: User
): Parcelable