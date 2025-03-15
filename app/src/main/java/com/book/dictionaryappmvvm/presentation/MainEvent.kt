package com.book.dictionaryappmvvm.presentation

import com.book.dictionaryappmvvm.data.dto.GitHubRepositoryDto

data class MainEvent(
    val isLoading : Boolean = false,
    val searchRepository : String = "",

    val respons : GitHubRepositoryDto? = null
)
