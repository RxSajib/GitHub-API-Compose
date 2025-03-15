package com.book.dictionaryappmvvm.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class GitHubRepositoryDto(
    @SerialName("incomplete_results")
    val incompleteResults: Boolean? = null,
    @SerialName("items")
    val items: List<Item>? = null,
    @SerialName("total_count")
    val totalCount: Int? = null
)