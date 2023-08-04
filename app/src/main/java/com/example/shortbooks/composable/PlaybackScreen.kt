package com.example.shortbooks.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.produceState
import com.example.shortbooks.playback.PlaybackStateFacade
import com.example.shortbooks.presentation.viewmodel.ShortBookViewModel
import kotlinx.coroutines.flow.debounce
import kotlin.coroutines.EmptyCoroutineContext
import androidx.compose.runtime.getValue

@Composable
fun PlaybackScreen(playbackStateFacade: PlaybackStateFacade, shortBookViewModel: ShortBookViewModel) {
    val totalSeconds by playbackStateFacade.totalDuration.collectAsState()
    val currentSeconds by playbackStateFacade.currentTime.collectAsState()
    val isPlaying by produceState(false, EmptyCoroutineContext) {
        playbackStateFacade.isPlaying
            .debounce(50)
            .collect { value = it }
    }
    val keyPointDescription by shortBookViewModel.currentKeyPointDescription.collectAsState()
    val totalSteps by shortBookViewModel.totalSteps.collectAsState()
    val currentStep by shortBookViewModel.currentStep.collectAsState()
    val canSkipForward by shortBookViewModel.canSkipForward.collectAsState()
    val canSkipBack by shortBookViewModel.canSkipBack.collectAsState()
    PlaybackContent(
        currentStep = currentStep,
        totalSteps = totalSteps,
        keyPointDescription = keyPointDescription,
        currentSeconds = currentSeconds,
        totalSeconds = totalSeconds,
        isPlaying = isPlaying,
        canSkipForward = canSkipForward,
        canSkipBack = canSkipBack,
        togglePlayState = { playbackStateFacade.togglePlayState() },
        onSeekBack = { playbackStateFacade.seekBack() },
        onSeekForward = { playbackStateFacade.seekForward() },
        onSkipBack = { shortBookViewModel.skipBack() },
        onSkipForward = { shortBookViewModel.skipForward() },
        applyManualTime = { playbackStateFacade.applyManualTime(it) }
    )
}