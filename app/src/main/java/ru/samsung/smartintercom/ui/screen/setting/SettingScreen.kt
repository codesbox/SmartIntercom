package ru.samsung.smartintercom.ui.screen.setting

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import ru.samsung.smartintercom.R
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
        
        
        val intercomInfo by viewModel.intercomInfo.collectAsState()
        val previousState by viewModel.previousInfo.collectAsState()
        var isHouseError by remember { mutableStateOf(isValidHouse(intercomInfo.house)) }
        var isRoomError by remember { mutableStateOf(isValidRoom(intercomInfo.room)) }
        var isVisible by remember { mutableStateOf(false) }
        
        LaunchedEffect(key1 = previousState, key2 = intercomInfo, block = {
            isVisible =
                !isHouseError && !isRoomError && (viewModel.previousInfo.value.house != viewModel.intercomInfo.value.house || viewModel.previousInfo.value.room != viewModel.intercomInfo.value.room)
        })
        
        Scaffold(modifier = Modifier.setupScreenData(this)) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddingValues)
            ) {
                IconButton(modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp) ,onClick = { navController.popBackStack() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.back),
                        contentDescription = stringResource(string.go_back)
                    )
                }
                
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    
                    
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
                            isVisible =
                                !isHouseError && !isRoomError && (viewModel.previousInfo.value.house != viewModel.intercomInfo.value.house || viewModel.previousInfo.value.room != viewModel.intercomInfo.value.room)
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
                            isVisible =
                                !isRoomError && !isHouseError && (viewModel.previousInfo.value.house != viewModel.intercomInfo.value.house || viewModel.previousInfo.value.room != viewModel.intercomInfo.value.room)
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
                            
                            isVisible =
                                !isHouseError && !isRoomError && (viewModel.previousInfo.value.house != viewModel.intercomInfo.value.house || viewModel.previousInfo.value.room != viewModel.intercomInfo.value.room)
                            
                        },
                        enabled = isVisible,
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
