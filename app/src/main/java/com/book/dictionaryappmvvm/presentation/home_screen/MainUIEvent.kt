package com.book.dictionaryappmvvm.presentation.home_screen

sealed class MainUIEvent {

    data class onSearchChange(val search : String) : MainUIEvent()

    object searcClick : MainUIEvent()
}