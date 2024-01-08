package ru.samsung.smartintercom.ui.screen.main

sealed interface MainState {
    data object Intro: MainState
    data object Loading: MainState
    // TODO: необходимо добавить состояние ошибки и отображения
}
