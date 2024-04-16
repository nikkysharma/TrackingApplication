package com.example.trackingapplication.presenter.data

import com.example.trackingapplication.data.TrackingData

sealed class TrackingIntent {
    data object GetTracking : TrackingIntent()
    data class  InsertTracking(val trackingData: TrackingData) : TrackingIntent()
}