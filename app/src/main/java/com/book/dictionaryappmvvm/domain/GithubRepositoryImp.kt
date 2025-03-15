package com.book.dictionaryappmvvm.domain

import android.util.Log
import com.book.dictionaryappmvvm.data.api.RepositoryAPI
import com.book.dictionaryappmvvm.data.dto.GitHubRepositoryDto
import com.book.dictionaryappmvvm.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

private const val TAG = "GithubRepositoryImp"
class GithubRepositoryImp constructor(
    private val api: RepositoryAPI
) : GithubRepository {
    override suspend fun getGitHubRepository(search: String): Flow<Result<GitHubRepositoryDto>> {
       return flow {
           emit(Result.Loading(isLoading = true))
           try {

               val response = api.getRepository(search = search)
               if(response.isSuccessful && response.body() != null){
                  /* emit(Result.Success(data = response.body()))
                   emit(Result.Loading(isLoading = false))
                   Log.d(TAG, "getGitHubRepository: success")
                   return@flow*/
                   emit(Result.Success(data = response.body()))
               }else {
                   emit(Result.Error(message = "Something went wrong"))
                 //  emit(Result.Loading(isLoading = false))
                  // Log.d(TAG, "getGitHubRepository: Something went wrong")
                  // return@flow
               }
           }catch (e: IOException) { // Handle no internet
               emit(Result.Error("No Internet Connection"))
           } catch (e: HttpException) { // Handle API errors
               emit(Result.Error("Server Error: ${e.message}"))
           } catch (e: Exception) { // Handle unexpected errors
               emit(Result.Error("Unexpected Error: ${e.localizedMessage}"))
           } finally {
               emit(Result.Loading(false))
           }
       }
    }
}