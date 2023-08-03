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
import androidx.compose.material3.ExperimentalMaterial3Api
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaybackContent() {
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
                        text = "Step 1/7",
                        modifier = Modifier.wrapContentHeight()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "One two description" + "\n".repeat(2),
                        textAlign = TextAlign.Center,
                        maxLines = 3,
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth()
                    )
                    SliderWithTime()
                    Spacer(modifier = Modifier.height(16.dp))
                    ExoPlayerControls()
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
        PlaybackContent()
    }
}