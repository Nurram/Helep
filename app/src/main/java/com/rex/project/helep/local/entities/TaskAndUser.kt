package com.rex.project.helep.local.entities

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.parcelize.Parcelize

@Parcelize
data class TaskAndUser(
    @Embedded
    val task: Task,
    @Relation(
        parentColumn = "userId",
        entityColumn = "id"
    )
    val user: User
): Parcelable