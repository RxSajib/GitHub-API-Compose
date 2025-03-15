package com.book.dictionaryappmvvm.di

import com.book.dictionaryappmvvm.data.api.RepositoryAPI
import com.book.dictionaryappmvvm.data.constant.DataManager
import com.book.dictionaryappmvvm.domain.GithubRepository
import com.book.dictionaryappmvvm.domain.GithubRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    private val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val clint : OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

    @Provides
    @Singleton
    fun provideAPI() : RepositoryAPI = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(DataManager.BASEURL)
        .client(clint)
        .build()
        .create(RepositoryAPI::class.java)


    @Provides
    @Singleton
    fun provideRepository(api : RepositoryAPI) : GithubRepository = GithubRepositoryImp(api)
}