package com.example.shortbooks.composable

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun SliderWithTime(
    currentSeconds: Float,
    totalSeconds: Float,
    applyManualTime: (timeSeconds: Float) -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isDragged by interactionSource.collectIsDraggedAsState()
    var isInteracting by remember { mutableStateOf(false) }

    val currentTime by rememberUpdatedState(currentSeconds)
    var manualTime by remember { mutableStateOf(currentSeconds) }

    val timeValueToShow by remember { derivedStateOf { if (isInteracting) manualTime else currentTime } }

    LaunchedEffect(totalSeconds) {
        // Delay not interacting update to prevent ui glitch
        combine(
            snapshotFlow { isPressed },
            snapshotFlow { isDragged }
        ) { isPressed, isDragged ->
            isPressed || isDragged
        }.onEach { interacting ->
            if (!interacting && isInteracting) {
                delay(300)
            }
            isInteracting = interacting
        }.launchIn(this)
    }


    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = formatSeconds(timeValueToShow),
            modifier = Modifier
                .wrapContentWidth()
                .padding(start = 16.dp)
        )
        Slider(
            value = timeValueToShow,
            onValueChange = {
                manualTime = it
            },
            onValueChangeFinished = {
                applyManualTime(manualTime)
            },
            interactionSource = interactionSource,
            valueRange = 0f..totalSeconds,
            modifier = Modifier
                .weight(8f)
                .padding(horizontal = 8.dp)
        )
        Text(
            text = formatSeconds(totalSeconds),
            modifier = Modifier
                .wrapContentWidth()
                .padding(end = 8.dp)
        )
    }
}

private fun formatSeconds(currentSeconds: Float) =
    "%02d:%02d".format((currentSeconds / 60).toInt(), (currentSeconds % 60).toInt())