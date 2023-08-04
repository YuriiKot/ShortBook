package com.example.shortbooks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.shortbooks.composable.PlaybackScreen
import com.example.shortbooks.playback.PlaybackStateFacade
import com.example.shortbooks.presentation.viewmodel.ShortBookViewModel
import com.example.shortbooks.ui.theme.ShortBooksTheme
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val playbackStateFacade: PlaybackStateFacade by inject()
    private val shortBookViewModel: ShortBookViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShortBooksTheme {
                PlaybackScreen(
                    playbackStateFacade,
                    shortBookViewModel,
                )
            }
        }
    }
}
