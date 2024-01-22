package ru.samsung.smartintercom.ui.screen.error

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavController
import ru.samsung.smartintercom.core.MainScreenId
import ru.samsung.smartintercom.ui.nav.Screen.LOADING
import ru.samsung.smartintercom.ui.nav.navigate
import ru.samsung.smartintercom.ui.screen.ScreenBaseData

object ErrorScreen : ScreenBaseData {
    override val name: String = ""
    
    @Composable
    override fun Render(navController: NavController) {
        Button(onClick = {
            navController.navigate(LOADING)
        }, modifier = Modifier.testTag(MainScreenId.buttonRetry)){
            Text("Повторить")
        }
    }
}