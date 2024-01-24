package ru.samsung.smartintercom.ui.screen.main

import androidx.compose.ui.graphics.ImageBitmap

sealed interface MainState {
    data object Intro : MainState
    data object Loading : MainState
    data object Error: MainState
    data class Intercom(val model: String = "", val firstEntry: Boolean = true, val image: ImageBitmap? = null) : MainState
}
