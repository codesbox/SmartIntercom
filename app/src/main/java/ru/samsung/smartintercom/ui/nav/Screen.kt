package ru.samsung.smartintercom.ui.nav

import ru.samsung.smartintercom.ui.screen.ScreenBaseData
import ru.samsung.smartintercom.ui.screen.call.CallScreen
import ru.samsung.smartintercom.ui.screen.main.MainScreen

/**
 * Класс, в котором перечислены все экраны
 *
 * @param route путь до экрана
 * @param baseScreen сущность экрана, которая будет отрендерена при открытии
 */
enum class Screen(val route: String, internal val baseScreen: ScreenBaseData) {
    MAIN("main", MainScreen),
    CALL("call", CallScreen),
}
