package com.book.dictionaryappmvvm.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class License(
    @SerialName("key")
    val key: String,
    @SerialName("name")
    val name: String,
    @SerialName("node_id")
    val nodeId: String,
    @SerialName("spdx_id")
    val spdxId: String,
    @SerialName("url")
    val url: String
)