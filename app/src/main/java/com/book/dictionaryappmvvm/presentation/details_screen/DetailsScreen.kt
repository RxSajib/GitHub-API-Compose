package com.book.dictionaryappmvvm.presentation.details_screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Details Repository") }
            )
        }
    ) { innderPadding ->
        Surface(
            modifier = Modifier.padding(innderPadding.calculateTopPadding())
        ) {

        }
    }
}