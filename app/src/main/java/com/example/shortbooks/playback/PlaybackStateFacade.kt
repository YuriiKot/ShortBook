package com.example.shortbooks.playback

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface PlaybackStateFacade {
    val totalDuration: MutableStateFlow<Float>
    val isPlaying: MutableStateFlow<Boolean>
    val currentTime: StateFlow<Float>
    val playEnded: MutableStateFlow<Boolean>
    fun setPlayback(url: String)
    fun togglePlayState()
    fun seekBack()
    fun seekForward()
    fun applyManualTime(time: Float)
}