package com.book.dictionaryappmvvm.presentation.home_screen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.book.dictionaryappmvvm.presentation.component.RepositoryItem
import com.book.dictionaryappmvvm.presentation.component.SearchView

private const val TAG = "HomeScreen"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val viewModel: MainViewModel = hiltViewModel()
    val state = viewModel.mainState.collectAsStateWithLifecycle()


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Home Screen") }
            )
        }

    ) {paddingValue ->

        Box(modifier = Modifier
            .padding(paddingValue)
            .fillMaxWidth()) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                SearchView(
                    value = state.value.searchRepository,
                    onValueChange = {
                        viewModel.onEvent(MainUIEvent.onSearchChange(search = it))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    onSearch = {
                        viewModel.onEvent(MainUIEvent.searcClick)
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))

                Log.d(TAG, "HomeScreen: ${state.value.errorMessage}")
                Box(modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)){
                    if(state.value.isLoading){
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(70.dp)
                                .align(alignment = Alignment.Center)
                        )
                    }else if(state.value.errorMessage  != null && state.value.respons == null){
                        Log.d(TAG, "HomeScreen: error message call")
                        Text(
                            text = "Error while server failed"
                        )
                    }else {
                        state.value.respons?.let { it ->
                            LazyColumn(modifier = Modifier.fillMaxSize()) {
                                items(it.items?.size ?: 0) {position ->
                                    it.items?.let {
                                        RepositoryItem(
                                            item = it[position],
                                            onclick = {
                                                Log.d(TAG, "HomeScreen: $it")
                                            }
                                        )
                                    }

                                }
                            }
                        }
                    }
                }

            }

        }
    }
}