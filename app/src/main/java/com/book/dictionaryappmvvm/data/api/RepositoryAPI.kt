package com.book.dictionaryappmvvm.data.api

import com.book.dictionaryappmvvm.data.dto.GitHubRepositoryDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoryAPI{

    @GET("search/repositories")
    suspend fun getRepository(
        @Query("q") search : String,
        @Query("sort") short : String = "stars",
        @Query("order") order : String = "desc"
    ) : Response<GitHubRepositoryDto>
}