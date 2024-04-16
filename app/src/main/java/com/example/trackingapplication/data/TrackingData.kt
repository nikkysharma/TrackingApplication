package com.example.trackingapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tracking")
data class TrackingData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val time:Long ,
    val location:String,
    val description:String)