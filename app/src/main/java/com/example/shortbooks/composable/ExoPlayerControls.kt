package com.example.shortbooks.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Forward10
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Replay5
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ExoPlayerControls(
    isPlaying: Boolean,
    canSkipForward: Boolean,
    canSkipBack: Boolean,
    togglePlayState: () -> Unit,
    onSeekBack: () -> Unit,
    onSeekForward: () -> Unit,
    onSkipBack: () -> Unit,
    onSkipForward: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ControlButton(
            imageVector = Icons.Default.Replay5,
            onClick = onSeekBack,
        )
        Spacer(modifier = Modifier.width(16.dp))
        ControlButton(
            imageVector = Icons.Default.SkipPrevious,
            onClick = onSkipBack,
            enabled = canSkipBack,
        )
        Spacer(modifier = Modifier.width(16.dp))
        ControlButton(
            imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
            onClick = { togglePlayState() }
        )
        Spacer(modifier = Modifier.width(16.dp))

        ControlButton(
            imageVector = Icons.Default.SkipNext,
            onClick = onSkipForward,
            enabled = canSkipForward,
        )
        Spacer(modifier = Modifier.width(16.dp))
        ControlButton(
            imageVector = Icons.Default.Forward10,
            onClick = onSeekForward,
        )
    }
}