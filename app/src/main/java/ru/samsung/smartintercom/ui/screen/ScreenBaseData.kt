package ru.samsung.smartintercom.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

/**
 * Базовый интерфейс экрана
 */
interface ScreenBaseData {
    /**
     * Название экрана
     */
    val name: String

    /**
     * Функция, вызываемая при отрисовки экрана
     *
     * @param navController контроллер, который необходим для открытия других экранов
     */
    @Composable
    fun Render(navController: NavController)
}
