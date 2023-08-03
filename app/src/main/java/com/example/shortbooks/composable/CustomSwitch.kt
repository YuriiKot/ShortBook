package com.example.shortbooks.composable

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AudioFile
import androidx.compose.material.icons.filled.ShortText
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomSwitch(
    height: Dp = 64.dp,
    width: Dp = height * 2,
    borderWidth: Dp = 2.dp,
    iconInnerPadding: Dp = 16.dp,
) {
    val gapBetweenThumbAndTrackEdge: Dp = borderWidth * 2

    val interactionSource = remember {
        MutableInteractionSource()
    }

    var switchOn by remember {
        mutableStateOf(true)
    }

    val alignment by animateAlignmentAsState(if (switchOn) 1f else -1f)

    Box(
        modifier = Modifier
            .size(width = width, height = height)
            .border(
                width = borderWidth,
                color = MaterialTheme.colorScheme.onSurface,
                shape = CircleShape
            )
            .clickable(
                indication = null,
                interactionSource = interactionSource
            ) {
                switchOn = !switchOn
            },
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .padding(
                    start = gapBetweenThumbAndTrackEdge,
                    end = gapBetweenThumbAndTrackEdge
                )
                .fillMaxSize(),
            contentAlignment = alignment,
        ) {
            Box(
                modifier = Modifier
                    .size(height - borderWidth * 4)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(all = iconInnerPadding),
            )
        }

        Icon(
            imageVector = Icons.Filled.ShortText,
            contentDescription = "Key notes",
            tint = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .size(size = height)
                .padding(all = iconInnerPadding)
                .align(alignment = Alignment.CenterStart)
        )

        Icon(
            imageVector = Icons.Filled.AudioFile,
            contentDescription = "Audio playback",
            tint = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .size(size = height)
                .padding(all = iconInnerPadding)
                .align(alignment = Alignment.CenterEnd)
        )
    }
}

@Composable
private fun animateAlignmentAsState(
    targetBiasValue: Float,
): State<BiasAlignment> {
    val bias by animateFloatAsState(targetBiasValue)
    return remember { derivedStateOf { BiasAlignment(horizontalBias = bias, verticalBias = 0f) } }
}

@Preview
@Composable
private fun PreviewCustomSwitch() {
    CustomSwitch()
}
