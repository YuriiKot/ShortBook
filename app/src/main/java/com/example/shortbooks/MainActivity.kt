package com.example.shortbooks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.shortbooks.composable.PlaybackContent
import com.example.shortbooks.ui.theme.ShortBooksTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShortBooksTheme {
                PlaybackContent()
            }
        }
    }
}
