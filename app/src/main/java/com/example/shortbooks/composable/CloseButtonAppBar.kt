package com.example.shortbooks.composable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CloseButtonAppBar() {
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(Icons.Filled.Close, "")
            }
        },
    )
}