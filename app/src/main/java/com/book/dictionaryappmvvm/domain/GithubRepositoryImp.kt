package com.book.dictionaryappmvvm.domain

import android.util.Log
import com.book.dictionaryappmvvm.data.api.RepositoryAPI
import com.book.dictionaryappmvvm.data.dto.GitHubRepositoryDto
import com.book.dictionaryappmvvm.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

private const val TAG = "GithubRepositoryImp"
class GithubRepositoryImp constructor(
    private val api: RepositoryAPI
) : GithubRepository {
    override suspend fun getGitHubRepository(search: String): Flow<Result<GitHubRepositoryDto>> {
       return flow {
           try {
               emit(Result.Loading(isLoading = true))
               val response = api.getRepository(search = search)
               if(response.isSuccessful && response.body() != null){
                   emit(Result.Success(data = response.body()))
                   emit(Result.Loading(isLoading = false))
                   Log.d(TAG, "getGitHubRepository: success")
                   return@flow
               }else {
                   emit(Result.Error(message = "Something went wrong"))
                   emit(Result.Loading(isLoading = false))
                   Log.d(TAG, "getGitHubRepository: Something went wrong")
                   return@flow
               }
           }catch (e : Exception){
               emit(Result.Error(message = e.message))
               emit(Result.Loading(isLoading = false))
               Log.d(TAG, "getGitHubRepository: ${e.message}")
               return@flow
           }
       }
    }
}