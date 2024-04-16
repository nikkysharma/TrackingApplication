package com.example.trackingapplication.data

import com.example.trackingapplication.TrackingDao
import com.example.trackingapplication.domain.TrackingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TrackingRepositoryImpl @Inject constructor(
    private val trackingDao: TrackingDao
) : TrackingRepository {
    override suspend fun getTracking(): List<TrackingData> =  withContext(Dispatchers.IO) {
        trackingDao.getAllTracking() }

    override suspend fun insertTrack(data: TrackingData)  =  withContext(Dispatchers.IO) {
        trackingDao.insertUser(data)
    }

}