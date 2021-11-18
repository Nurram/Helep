package com.rex.project.helep.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class TaskWithBiddings(
    @Embedded
    val task: Task,
    @Relation(
        parentColumn = "id",
        entityColumn = "taskId"
    )
    val biddings: List<Bidding>
)