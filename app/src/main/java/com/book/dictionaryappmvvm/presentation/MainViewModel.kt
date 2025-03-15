package com.book.dictionaryappmvvm.presentation

import android.util.Log
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.book.dictionaryappmvvm.domain.GithubRepository
import com.book.dictionaryappmvvm.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private var job : Job? = null

    init {
        _mainState.update {
            it.copy(searchRepository = "Android")
        }
        job?.cancel()
        job = viewModelScope.launch {
            searchNewQuery()
        }

    }

    fun onEvent(mainUIEvent: MainUIEvent){
        when(mainUIEvent){
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

    private fun searchNewQuery(){
        viewModelScope.launch {
            repository.getGitHubRepository(
                search = mainState.value.searchRepository
            ).collect {result ->
                when(result){
                    is Result.Loading -> {
                        _mainState.update {event ->
                            event.copy(isLoading = result.isLoading)
                        }
                    }
                    is Result.Success -> {
                        result.data?.let { githubResult ->
                            _mainState.update {
                                it.copy( respons = githubResult.copy())
                            }
                        }
                    }
                    is Result.Error -> {
                        null
                    }
                }
            }
        }
    }
}