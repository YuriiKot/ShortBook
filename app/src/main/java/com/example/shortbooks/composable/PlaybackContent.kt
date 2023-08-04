package com.example.shortbooks.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shortbooks.R
import com.example.shortbooks.ui.theme.ShortBooksTheme

@Composable
fun PlaybackContent(
    currentStep: Int,
    totalSteps: Int,
    keyPointDescription: String,
    currentSeconds: Float,
    totalSeconds: Float,
    isPlaying: Boolean,
    canSkipForward: Boolean,
    canSkipBack: Boolean,
    togglePlayState: () -> Unit,
    onSeekBack: () -> Unit,
    onSeekForward: () -> Unit,
    onSkipBack: () -> Unit,
    onSkipForward: () -> Unit,
    applyManualTime: (timeSeconds: Float) -> Unit,
) {
    Scaffold(
        topBar = {
            CloseButtonAppBar()
        },
        content = { padding ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.antifragile_cover), // Replace with the actual resource ID of your book cover
                    contentDescription = "Book Cover",
                    modifier = Modifier
                        .weight(1f)
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .wrapContentHeight()
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Key point ${currentStep + 1} of $totalSteps".uppercase(),
                        modifier = Modifier.wrapContentHeight()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = keyPointDescription + "\n ".repeat(2),
                        textAlign = TextAlign.Center,
                        maxLines = 3,
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth()
                    )
                    SliderWithTime(
                        currentSeconds = currentSeconds,
                        totalSeconds = totalSeconds,
                        applyManualTime = applyManualTime,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    ExoPlayerControls(
                        isPlaying = isPlaying,
                        canSkipForward = canSkipForward,
                        canSkipBack = canSkipBack,
                        togglePlayState = togglePlayState,
                        onSeekBack = onSeekBack,
                        onSeekForward = onSeekForward,
                        onSkipBack = onSkipBack,
                        onSkipForward = onSkipForward,
                    )
                    Spacer(modifier = Modifier.height(64.dp))
                    CustomSwitch()
                    Spacer(modifier = Modifier.height(16.dp))
                }

            }
        }
    )
}

@Preview
@Composable
fun PlaybackContentPreview() {
    ShortBooksTheme {
        PlaybackContent(
            currentStep = 1,
            totalSteps = 2,
            keyPointDescription = "Description of key point 1",
            currentSeconds = 12f,
            totalSeconds = 120f,
            isPlaying = true,
            canSkipForward = true,
            canSkipBack = false,
            togglePlayState = {  },
            onSeekBack = {  },
            onSeekForward = { },
            onSkipBack = {  },
            onSkipForward = {  },
            applyManualTime = { }
        )
    }
}