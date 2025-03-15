package com.book.dictionaryappmvvm.presentation

sealed class MainUIEvent {

    data class onSearchChange(val search : String) : MainUIEvent()

    object searcClick : MainUIEvent()
}