package ru.samsung.smartintercom.ui.nav

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

/**
 * Данная функция инициализирует граф инициализации
 */
@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.MAIN.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    )
    {
        // Здесь происходит перебор функций для инициализации всех возможных экранов
        Screen.entries.forEach { screen ->
            composable(route = screen.route) {
                screen.baseScreen.Render(navController)
            }
        }
    }
}

fun NavController.navigate(screen: Screen) {
    navigate(route = screen.route)
}
