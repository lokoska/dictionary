//package mvi

import react.RState

data class ApplicationState(
    val deployTime: String = "",
    val dictionary: Dictionary? = null
) : RState

data class Dictionary(
    val name: String,
    val words: List<Word>
)

data class Word(
    val hint: String,
    val secret: String
)
