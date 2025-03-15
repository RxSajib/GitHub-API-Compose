package com.book.dictionaryappmvvm.presentation.home_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.book.dictionaryappmvvm.domain.GithubRepository
import com.book.dictionaryappmvvm.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MainViewModel"

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: GithubRepository
) : ViewModel() {

    private val _mainState = MutableStateFlow(MainEvent())
    val mainState = _mainState.asStateFlow()

    private var job: Job? = null

    init {
        _mainState.update {
            it.copy(searchRepository = "Android")
        }
        job?.cancel()
        job = viewModelScope.launch {
            searchNewQuery()
        }

    }

    fun onEvent(mainUIEvent: MainUIEvent) {
        when (mainUIEvent) {
            is MainUIEvent.onSearchChange -> {
                _mainState.update {
                    it.copy(searchRepository = mainUIEvent.search.toLowerCase())
                }
            }

            is MainUIEvent.searcClick -> {
                job?.cancel()
                job = viewModelScope.launch {
                    searchNewQuery()
                }
            }
        }
    }

    private fun searchNewQuery() {
        _mainState.value = _mainState.value.copy(
            isLoading = false,
            errorMessage = null,
            respons = null
        ) // Ensure state is updated immediately

        job?.cancel()
        job = viewModelScope.launch {
            repository.getGitHubRepository(
                search = mainState.value.searchRepository
            ).collectLatest { result ->
                when (result) {
                    is Result.Loading -> {
                        _mainState.update { event ->
                            event.copy(isLoading = result.isLoading, errorMessage = null)
                        }
                    }

                    is Result.Success -> {
                        Log.d(TAG, "searchNewQuery: success")
                        result.data?.let { githubResult ->
                            _mainState.update {
                                it.copy(respons = githubResult, errorMessage = null)
                            }
                        }
                    }

                    is Result.Error -> {

                        _mainState.update {
                            it.copy(errorMessage = result.message ?: "Unknown Error", isLoading = false)
                        }

                        Log.d(TAG, "searchNewQuery: error message ${mainState.value.errorMessage}")
                    }
                }
            }
        }
    }
}