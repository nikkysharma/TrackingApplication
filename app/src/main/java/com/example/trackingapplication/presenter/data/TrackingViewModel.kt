package com.example.trackingapplication.presenter.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackingapplication.data.TrackingData
import com.example.trackingapplication.domain.TrackingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrackingViewModel @Inject constructor(private val trackingRepository: TrackingRepository) : ViewModel() {
    var tracking = Channel<TrackingIntent>()
    init {
        viewModelScope.launch {
            val flow = tracking.consumeAsFlow()
            flow.collect {
                when(it){
                    TrackingIntent.GetTracking -> {
                        getTracking()
                    }

                    is TrackingIntent.InsertTracking -> {
                        insertTrack(it.trackingData)
                    }
                }
            }
        }
    }
    private val _trackStateFlow = MutableStateFlow<TrackingUiState?>(null)

    val trackStateFlow = _trackStateFlow.asStateFlow()

    private fun getTracking() {
        viewModelScope.launch {
            _trackStateFlow.emit(TrackingUiState.Loading)
            trackingRepository.getTracking().also {
                _trackStateFlow.emit(TrackingUiState.GetTracks(it))
            }
        }
    }

    suspend fun insertTrack(data: TrackingData){
        viewModelScope.launch {
            trackingRepository.insertTrack(data)
            trackingRepository.getTracking().also {
                _trackStateFlow.emit(TrackingUiState.GetTracks(it))
            }
        }
    }
}