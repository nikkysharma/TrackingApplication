package com.example.trackingapplication.domain

import com.example.trackingapplication.data.TrackingData
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow

interface TrackingRepository {
    suspend fun getTracking() : List<TrackingData>
    suspend fun insertTrack(data: TrackingData)
}