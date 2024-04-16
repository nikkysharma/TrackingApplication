package com.example.trackingapplication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.trackingapplication.data.TrackingData
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackingDao {

    @Query("SELECT * FROM tracking")
    fun getAllTracking(): List<TrackingData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(trackingData: TrackingData)

}