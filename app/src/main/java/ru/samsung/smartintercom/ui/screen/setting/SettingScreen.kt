package ru.samsung.smartintercom.ui.screen.setting

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import ru.samsung.smartintercom.core.SettingScreenId
import ru.samsung.smartintercom.ui.nav.Screen.MAIN
import ru.samsung.smartintercom.ui.nav.navigate
import ru.samsung.smartintercom.ui.screen.ScreenBaseData
import ru.samsung.smartintercom.ui.screen.main.MainViewModel

object SettingScreen : ScreenBaseData {
    override val name = SettingScreenId.screenId
    
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Render(navController: NavController) {
        val viewModel = koinViewModel<SettingViewModel>()
        
        LaunchedEffect(key1 = Unit, block = {
            viewModel.loadIntercomInfo()
        })
        
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val intercomInfo by viewModel.intercomInfo.collectAsState()
                
                OutlinedTextField(
                    modifier = Modifier
                        .testTag(SettingScreenId.inputHouse)
                        .padding(vertical = 8.dp),
                    value = intercomInfo.house,
                    onValueChange = {
                        viewModel.changeHouse(it)
                    },
                    placeholder = {
                        Text(text = "Enter house address")
                    },
                )
                
                OutlinedTextField(
                    modifier = Modifier
                        .testTag(SettingScreenId.inputFlat)
                        .padding(vertical = 8.dp),
                    value = intercomInfo.room,
                    onValueChange = { viewModel.changeFlat(it) },
                    placeholder = {
                        Text(text = "Enter flat number")
                    },
                )
                
                val scope = rememberCoroutineScope()
                
                Button(
                    modifier = Modifier
                        .testTag(SettingScreenId.buttonSave)
                        .fillMaxWidth(0.75f)
                        .padding(vertical = 12.dp),
                    onClick = {
                        scope.launch {
                            viewModel.loadToStorage()
                        }
                        
                        navController.popBackStack()
                    },
                ) {
                    Text("Save")
                }
            }
        }
    }
}
