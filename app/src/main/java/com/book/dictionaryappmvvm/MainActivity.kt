package com.book.dictionaryappmvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.book.dictionaryappmvvm.presentation.home_screen.HomeScreen
import com.book.dictionaryappmvvm.ui.theme.DictionaryAppMVVMTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DictionaryAppMVVMTheme {
                HomeScreen()
            }
        }
    }
}

