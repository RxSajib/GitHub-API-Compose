package com.book.dictionaryappmvvm.presentation.home_screen

import com.book.dictionaryappmvvm.data.dto.GitHubRepositoryDto

data class MainEvent(
    val isLoading : Boolean = false,
    val searchRepository : String = "",
    val respons : GitHubRepositoryDto? = null,
    val errorMessage : String? = null
)