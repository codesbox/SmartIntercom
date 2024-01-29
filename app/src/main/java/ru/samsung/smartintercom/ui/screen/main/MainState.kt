package ru.samsung.smartintercom.ui.screen.main

import androidx.compose.ui.graphics.ImageBitmap
import ru.samsung.smartintercom.domain.intercom.IntercomModel

sealed interface MainState {
    data object Intro : MainState
    data object Loading : MainState
    data object Error: MainState
    data class Intercom(val model: IntercomModel = IntercomModel(""), val firstEntry: Boolean = true, val image: ImageBitmap? = null, val error: String? = null) : MainState
}
