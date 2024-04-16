package com.example.trackingapplication.presenter.data

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.example.trackingapplication.data.TrackingData
import kotlinx.coroutines.launch
import java.util.Random

@Composable
fun TrackListScreen() {
    val trackViewModel: TrackingViewModel = hiltViewModel()
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val lifecycleOwner = LocalLifecycleOwner.current
    val flowLifecycleAware = remember(trackViewModel.trackStateFlow, lifecycleOwner) {
        trackViewModel.trackStateFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val trackState by flowLifecycleAware.collectAsState(initial = TrackingUiState.Idel)
    val corotineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        trackViewModel.tracking.send(TrackingIntent.GetTracking)
    }
    Column {
       Button(onClick = { corotineScope.launch {
           val id = Random().nextInt(61) + 20
           val trackingData = TrackingData(id, id.toLong(),"Location $id", "Description $id")
           trackViewModel.tracking.send(TrackingIntent.InsertTracking(trackingData))
       }}) {
           Text(text = "Add")
       }
        Box(modifier = Modifier) {
            val context = LocalContext.current
            when (trackState) {
                is TrackingUiState.Loading -> CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )

                is TrackingUiState.GetTracks -> {
                    TrackList(track = (trackState as TrackingUiState.GetTracks).data)
                }


                is TrackingUiState.Error -> {}
                TrackingUiState.Idel -> {}
                else -> {}
            }
        }
    }
}

@Composable
fun TrackList(track: List<TrackingData>) {
    LazyColumn(modifier = Modifier.padding(bottom = 56.dp), content = {
        items(track) { trackItem ->
            TrackRow(trackItem)
        }
    })
}

@Composable
private fun TrackRow(tracks: TrackingData) {
    Card(
        modifier = Modifier
            .padding(all = 10.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(all = 10.dp)) {
            Text(
                tracks.time.toString(),
                fontSize = 25.sp,
                fontWeight = FontWeight.W700,
                modifier = Modifier.padding(10.dp)
            )
            Text(tracks.location, color = Color.Gray, modifier = Modifier.padding(10.dp))
            Text(tracks.description, color = Color.Blue, modifier = Modifier.padding(10.dp))
        }
    }
}