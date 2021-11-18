package com.rex.project.helep.local.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rex.project.helep.local.entities.Bidding

@Dao
interface BiddingDao {

    @Insert
    suspend fun insert(bidding: Bidding)
}