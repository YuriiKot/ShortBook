package com.example.shortbooks.composable

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.example.shortbooks.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transform
import java.io.File

@SuppressLint("UnsafeOptInUsageError")
class PlaybackStateFacade(context: Context) : CoroutineScope {
    override val coroutineContext = Job() + Dispatchers.Main
    val uri = Uri.parse(
        ContentResolver.SCHEME_ANDROID_RESOURCE
                + File.pathSeparator + File.separator + File.separator
                + context.packageName
                + File.separator
                + R.raw.antifragile_1
    )

    val totalDuration = MutableStateFlow(0f)
    val isPlaying = MutableStateFlow(false)

    private val playbackTime = isPlaying
        .transform { isPlaying ->
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
        .stateIn(this, SharingStarted.Eagerly, 0f)

    private val manualTime = MutableStateFlow(0f)

    val currentTime = merge(playbackTime, manualTime)
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
            totalDuration.value = totalDurationSeconds(player)
        }

        override fun onIsPlayingChanged(playing: Boolean) {
            isPlaying.value = playing
        }
    }

    private fun totalDurationSeconds(player: Player) = player.duration.coerceAtLeast(0L).div(1000f)

    private fun currentDurationSeconds() = player.currentPosition.coerceAtLeast(0L).div(1000f)

    fun setPlayback() {
        with(player) {
            setMediaItem(
                MediaItem.fromUri(
                    uri
                )
            )
            prepare()
            playWhenReady = true
        }
    }

    fun togglePlayState() {
        if (player.isPlaying) player.pause() else player.play()
    }

    fun pause() {
        player.pause()
    }

    fun play() {
        player.play()
    }

    fun seekTo(time: Float) {
        player.seekTo(time.toLong())
        play()
        manualTime.value = currentDurationSeconds()
    }

    fun seekBack() {
        player.seekBack()
    }

    fun seekForward() {
        player.seekForward()
    }

    fun setManualTime(time: Float) {
        manualTime.value = time
        if (player.isPlaying) pause()
    }

    fun applyManualTime() {
        seekTo(manualTime.value * 1000)
    }
}