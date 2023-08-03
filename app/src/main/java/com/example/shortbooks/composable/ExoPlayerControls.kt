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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun ExoPlayerControls() {
    var isPlaying by remember { mutableStateOf(false) }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ControlButton(
            imageVector = Icons.Default.Replay5,
            onClick = { /* Handle rewind action */ }
        )
        Spacer(modifier = Modifier.width(16.dp))
        ControlButton(
            imageVector = Icons.Default.SkipPrevious,
            onClick = { /* Handle fast forward action */ }
        )
        Spacer(modifier = Modifier.width(16.dp))
        ControlButton(
            imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
            onClick = { isPlaying = !isPlaying }
        )
        Spacer(modifier = Modifier.width(16.dp))

        ControlButton(
            imageVector = Icons.Default.SkipNext,
            onClick = { /* Handle fast forward action */ }
        )
        Spacer(modifier = Modifier.width(16.dp))
        ControlButton(
            imageVector = Icons.Default.Forward10,
            onClick = { /* Handle fast forward action */ }
        )
    }
}