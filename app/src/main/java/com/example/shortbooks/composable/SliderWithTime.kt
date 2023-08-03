package com.example.shortbooks.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun SliderWithTime() {
    var currentTime by remember { mutableStateOf(0f) }
    val totalTime = 100f // Replace with the actual total time of your media

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "00:12",
            modifier = Modifier
                .wrapContentWidth()
                .padding(start = 16.dp)
        )
        Slider(
            value = currentTime,
            onValueChange = { currentTime = it },
            valueRange = 0f..totalTime,
            steps = 100,
            modifier = Modifier
                .weight(8f)
                .padding(horizontal = 8.dp)
        )
        Text(
            text = "00:15",
            modifier = Modifier
                .wrapContentWidth()
                .padding(end = 8.dp)
        )
    }
}