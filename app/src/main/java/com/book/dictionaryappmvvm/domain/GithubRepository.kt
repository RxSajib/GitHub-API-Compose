package com.book.dictionaryappmvvm.domain

import com.book.dictionaryappmvvm.data.dto.GitHubRepositoryDto
import com.book.dictionaryappmvvm.utils.Result
import kotlinx.coroutines.flow.Flow

interface GithubRepository {

    suspend fun getGitHubRepository(search : String) : Flow<Result<GitHubRepositoryDto>>
}