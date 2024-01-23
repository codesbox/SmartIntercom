package ru.samsung.smartintercom.ui.screen.callhistory

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import ru.samsung.smartintercom.core.CallHistoryId
import ru.samsung.smartintercom.ui.screen.ScreenBaseData

object CallHistoryScreen : ScreenBaseData {
    override val name = CallHistoryId.screenId
    
    @Composable
    override fun Render(navController: NavController) {
    
    }
}