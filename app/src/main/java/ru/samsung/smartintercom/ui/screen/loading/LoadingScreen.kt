package ru.samsung.smartintercom.ui.screen.loading

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel
import ru.samsung.smartintercom.core.MainScreenId
import ru.samsung.smartintercom.ui.nav.Screen.INTERCOM_INFO
import ru.samsung.smartintercom.ui.nav.Screen.SETTING
import ru.samsung.smartintercom.ui.nav.navigate
import ru.samsung.smartintercom.ui.screen.ScreenBaseData
import ru.samsung.smartintercom.ui.screen.main.MainState
import ru.samsung.smartintercom.ui.theme.SmartIntercomTheme
import ru.samsung.smartintercom.utils.setupScreenData

object LoadingScreen : ScreenBaseData {
    override val name = ""
    
    @Composable
    override fun Render(navController: NavController) {
        
        val viewModel = koinViewModel<LoadingViewModel>()
        val context = LocalContext.current.applicationContext
        
        LaunchedEffect(key1 = Unit, block = {
            viewModel.load(context)
            viewModel.loadingState.collectLatest {
                if (it is LoadingState.Received && it.info.house != "") {
                    navController.navigate(INTERCOM_INFO)
                }
            }
        })
        
        Column {
            
            Button(
                onClick = { navController.navigate(SETTING) },
                modifier = Modifier.testTag(MainScreenId.buttonSettings)
            ) {
                Text("переход в настройки")
            }
        }
    }
    
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    internal fun RenderState(
        state: MainState,
        openSettingClick: () -> Unit,
    ) {
        Scaffold(modifier = Modifier.setupScreenData(this), content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
            
            }
        })
    }
}

@Preview(showBackground = true, device = "spec:width=1080px,height=1920px,dpi=320")
@Composable
fun RenderLoadingPreview() {
    val mockAction = {}
    SmartIntercomTheme {
        Row {
            LoadingScreen.RenderState(
                state = MainState.Intro,
                openSettingClick = mockAction,
            )
        }
    }
}
