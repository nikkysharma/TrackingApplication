package com.example.trackingapplication.presenter.data

import com.example.trackingapplication.data.TrackingData
import kotlinx.coroutines.flow.Flow

sealed class TrackingUiState {
    object Idel :TrackingUiState()
    object Loading : TrackingUiState()
    data class GetTracks(val data: List<TrackingData>) : TrackingUiState()
    data class Error(val message :String) :TrackingUiState()
}