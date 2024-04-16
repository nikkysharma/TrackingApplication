package com.example.trackingapplication

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.trackingapplication.data.TrackingData
import dagger.hilt.android.qualifiers.ApplicationContext
@Database(entities = [TrackingData::class], version = 1)
abstract class TrackingRoomDataBase :RoomDatabase() {
    abstract fun trackDao(): TrackingDao
}