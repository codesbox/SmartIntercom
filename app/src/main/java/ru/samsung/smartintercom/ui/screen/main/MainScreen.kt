package ru.samsung.smartintercom.ui.screen.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel
import ru.samsung.smartintercom.R
import ru.samsung.smartintercom.core.MainScreenId
import ru.samsung.smartintercom.ui.screen.ScreenBaseData
import ru.samsung.smartintercom.ui.theme.SmartIntercomTheme
import ru.samsung.smartintercom.ui.theme.button
import ru.samsung.smartintercom.utils.setupScreenData

object MainScreen : ScreenBaseData {
    override val name = MainScreenId.screenId

    @Composable
    override fun Render(navController: NavController) {

        val viewModel: MainViewModel = koinViewModel()
        val state by viewModel.uiState.collectAsState()
        RenderState(
            state = state,
            openSettingClick = {},
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    internal fun RenderState(
        state: MainState,
        openSettingClick: () -> Unit,
    ) {
        Scaffold(
            modifier = Modifier.setupScreenData(this),
            content = { paddingValues ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    when (state) {
                        is MainState.Intro -> {
                            IntroState(
                                openSettingClick = openSettingClick
                            )
                        }

                        is MainState.Loading -> TODO("Добавить состояние загрузки")
                    }
                }
            }
        )
    }

    @Composable
    private fun IntroState(
        openSettingClick: () -> Unit,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.main_screen_intro_title),
                    style = MaterialTheme.typography.displayLarge,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(R.string.main_screen_intro_text),
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center,
                )
            }
            Button(
                modifier = Modifier.testTag(MainScreenId.buttonStart),
                onClick = openSettingClick
            ) {
                Text(
                    text = stringResource(id = R.string.main_screen_intro_button),
                    style = MaterialTheme.typography.button.normal
                )
            }
        }
    }
}

@Preview(showBackground = true, device = "spec:width=1080px,height=1920px,dpi=320")
@Composable
fun RenderPreview() {
    val mockAction = {}
    SmartIntercomTheme {
        Row {
            MainScreen.RenderState(
                state = MainState.Intro,
                openSettingClick = mockAction,
            )
        }
    }
}
