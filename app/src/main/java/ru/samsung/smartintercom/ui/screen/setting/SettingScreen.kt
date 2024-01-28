package ru.samsung.smartintercom.ui.screen.setting

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import ru.samsung.smartintercom.R.string
import ru.samsung.smartintercom.core.SettingScreenId
import ru.samsung.smartintercom.ui.screen.ScreenBaseData
import ru.samsung.smartintercom.ui.theme.button
import ru.samsung.smartintercom.utils.setupScreenData

object SettingScreen : ScreenBaseData {
    override val name = SettingScreenId.screenId
    
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Render(navController: NavController) {
        val viewModel = koinViewModel<SettingViewModel>()
        
        LaunchedEffect(key1 = Unit, block = {
            viewModel.loadIntercomInfo()
        })
        
        Scaffold(modifier = Modifier.setupScreenData(this)) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddingValues)
            ) {
                
                
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    
                    
                    val intercomInfo by viewModel.intercomInfo.collectAsState()
                    var isHouseError by remember { mutableStateOf(false) }
                    var isRoomError by remember { mutableStateOf(false) }
                    
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(vertical = 64.dp),
                        text = stringResource(string.intercom_settings),
                        style = MaterialTheme.typography.displayMedium,
                        textAlign = TextAlign.Center,
                    )
                    
                    OutlinedTextField(
                        modifier = Modifier
                            .testTag(SettingScreenId.inputHouse)
                            .padding(vertical = 8.dp),
                        value = intercomInfo.house,
                        onValueChange = {
                            viewModel.changeHouse(it)
                            isHouseError = !isValidHouse(it)
                        },
                        placeholder = {
                            Text(text = stringResource(string.enter_house_number))
                        },
                        maxLines = 1,
                        isError = isHouseError,
                    )
                    
                    OutlinedTextField(
                        modifier = Modifier
                            .testTag(SettingScreenId.inputFlat)
                            .padding(vertical = 8.dp),
                        value = intercomInfo.room,
                        onValueChange = {
                            viewModel.changeFlat(it)
                            isRoomError = !isValidRoom(it)
                        },
                        placeholder = {
                            Text(text = stringResource(string.enter_room_number))
                        },
                        maxLines = 1,
                        isError = isRoomError,
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
                        enabled = !isHouseError and !isRoomError,
                    ) {
                        Text(
                            stringResource(string.save_button),
                            style = MaterialTheme.typography.button.normal
                        )
                    }
                }
            }
        }
        
    }
    
    private fun isValidHouse(house: String): Boolean {
        val houseRegex = """(?=^.{1,4}${'$'})(\d+(/?[a-e])?)""".toRegex()
        return houseRegex.matches(house)
    }
    
    private fun isValidRoom(room: String): Boolean {
        val roomRegex = """(?=^.{1,6}${'$'})(\d+)""".toRegex()
        return roomRegex.matches(room)
    }
    
}
