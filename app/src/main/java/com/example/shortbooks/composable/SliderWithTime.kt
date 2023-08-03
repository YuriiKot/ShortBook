package com.example.shortbooks.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SliderWithTime(
    currentSeconds: Float,
    totalSeconds: Float,
    postManualTime: (timeSeconds: Float) -> Unit,
    applyManualTime: (timeSeconds: Float) -> Unit,
) {

    var manualTime by remember { mutableStateOf(currentSeconds) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = formatSeconds(currentSeconds),
            modifier = Modifier
                .wrapContentWidth()
                .padding(start = 16.dp)
        )
        Slider(
            value = currentSeconds,
            onValueChange = {
                manualTime = it
                postManualTime(it)
            },
            onValueChangeFinished = {
                applyManualTime(manualTime)
            },
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