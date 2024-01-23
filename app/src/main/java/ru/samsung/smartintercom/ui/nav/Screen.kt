package ru.samsung.smartintercom.ui.nav

import ru.samsung.smartintercom.ui.screen.ScreenBaseData
import ru.samsung.smartintercom.ui.screen.call.CallScreen
import ru.samsung.smartintercom.ui.screen.callhistory.CallHistoryScreen
import ru.samsung.smartintercom.ui.screen.main.MainScreen
import ru.samsung.smartintercom.ui.screen.setting.SettingScreen

/**
 * Класс, в котором перечислены все экраны
 *
 * @param route путь до экрана
 * @param baseScreen сущность экрана, которая будет отрендерена при открытии
 */
enum class Screen(val route: String, internal val baseScreen: ScreenBaseData) {
    MAIN("main", MainScreen),
    CALL("call", CallScreen),
    SETTING("setting", SettingScreen),
    HISTORY("history", CallHistoryScreen)
}
