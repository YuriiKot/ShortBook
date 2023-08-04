package com.example.shortbooks.playback

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.Player.STATE_ENDED
import androidx.media3.exoplayer.ExoPlayer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transformLatest

@SuppressLint("UnsafeOptInUsageError")
class PlaybackStateFacadeImpl(context: Context) : PlaybackStateFacade, CoroutineScope {
    override val coroutineContext = Job() + Dispatchers.Main

    override val totalDuration = MutableStateFlow(0f)
    override val isPlaying = MutableStateFlow(false)
    override val playEnded = MutableStateFlow(false)

    private val playbackTime = isPlaying
        .transformLatest { isPlaying ->
            if (isPlaying) {
                while (true) {
                    emit(Unit)
                    delay(200)
                }
            }
        }
        .map {
            currentDurationSeconds()
        }
        .onEach { println("DEBUGGING playbackTime $it") }
        .stateIn(this, SharingStarted.Eagerly, 0f)

    private val manualTime = MutableStateFlow(0f)

    init {
        manualTime.onEach { println("DEBUGGING manual time $it") }
            .launchIn(this)
    }

    override val currentTime = merge(playbackTime, manualTime)
        .stateIn(this, SharingStarted.Eagerly, 0f)

    private val player by lazy {
        ExoPlayer.Builder(context)
            .setSeekBackIncrementMs(5000)
            .setSeekForwardIncrementMs(10000)
            .build()
            .apply {
                addListener(listener)
            }
    }

    private val listener = object : Player.Listener {
        override fun onEvents(player: Player, events: Player.Events) {
            super.onEvents(player, events)
            val totalDurationSeconds = totalDurationSeconds(player)
            if (totalDurationSeconds > 0) {
                totalDuration.value = totalDurationSeconds
            }
        }

        override fun onIsPlayingChanged(playing: Boolean) {
            isPlaying.value = playing
        }

        override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
            playEnded.value = playbackState == STATE_ENDED
        }
    }

    private fun totalDurationSeconds(player: Player) = player.duration.coerceAtLeast(0L).div(1000f)

    private fun currentDurationSeconds() = player.currentPosition.coerceAtLeast(0L).div(1000f)

    override fun setPlayback(url: String) {
        with(player) {
            setMediaItem(
                MediaItem.fromUri(
                    Uri.parse(url)
                )
            )
            prepare()
            playWhenReady = true
        }
        manualTime.value = 0f
    }

    override fun togglePlayState() {
        if (player.isPlaying) player.pause() else player.play()
    }

    private fun pause() {
        player.pause()
    }

    private fun play() {
        player.play()
    }

    private fun seekTo(time: Float) {
        player.seekTo(time.toLong())
        play()
    }

    override fun seekBack() {
        player.seekBack()
    }

    override fun seekForward() {
        player.seekForward()
    }

    override fun applyManualTime(time: Float) {
        seekTo(time * 1000)
    }
}