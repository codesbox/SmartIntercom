package ru.samsung.smartintercom.ui.screen.setting

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import ru.samsung.smartintercom.R
import ru.samsung.smartintercom.core.MainScreenId
import ru.samsung.smartintercom.core.SettingScreenId
import ru.samsung.smartintercom.ui.screen.ScreenBaseData
import ru.samsung.smartintercom.ui.screen.setting.SettingViewModel
import ru.samsung.smartintercom.ui.theme.SmartIntercomTheme
import ru.samsung.smartintercom.ui.theme.button
import ru.samsung.smartintercom.utils.setupScreenData

object SettingScreen : ScreenBaseData {
    override val name = SettingScreenId.screenId
    
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Render(navController: NavController) {
        
        
        val viewModel = koinViewModel<SettingViewModel>()
        
        val context = LocalContext.current
        LaunchedEffect(key1 = Unit, block = {
            viewModel.loadIntercomInfo()
        })
        
        Column(modifier = Modifier.fillMaxSize()) {
            val intercomInfo by viewModel.intercomInfo.collectAsState()
            
            TextField(value = intercomInfo.house, onValueChange = {
                viewModel.changeHouse(it)
            }, modifier = Modifier.testTag(SettingScreenId.inputHouse))
            
            TextField(
                value = intercomInfo.room,
                onValueChange = { viewModel.changeFlat(it) },
                modifier = Modifier.testTag(SettingScreenId.inputFlat)
            )
            val scope = rememberCoroutineScope()
            Button(modifier = Modifier.testTag(SettingScreenId.buttonSave), onClick = {
                scope.launch {
                    viewModel.loadToStorage()
                }
            }) {
                Text("Save")
            }
        }
    }
}

@Preview(showBackground = true, device = "spec:width=1080px,height=1920px,dpi=320")
@Composable
fun RenderSettingsPreview() {
    val mockAction = {}
    SmartIntercomTheme {
        Row {
        
        }
    }
}
