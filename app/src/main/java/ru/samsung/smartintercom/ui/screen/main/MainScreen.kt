package ru.samsung.smartintercom.ui.screen.main

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.samsung.smartintercom.R
import ru.samsung.smartintercom.core.MainScreenId
import ru.samsung.smartintercom.ui.nav.Screen.HISTORY
import ru.samsung.smartintercom.ui.nav.Screen.SETTING
import ru.samsung.smartintercom.ui.nav.navigate
import ru.samsung.smartintercom.ui.screen.ScreenBaseData
import ru.samsung.smartintercom.ui.theme.SmartIntercomTheme
import ru.samsung.smartintercom.ui.theme.button
import ru.samsung.smartintercom.utils.setupScreenData

object MainScreen : ScreenBaseData {
    override val name = MainScreenId.screenId
    
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Render(navController: NavController) {
        
        val viewModel: MainViewModel = koinViewModel()
        val state by viewModel.uiState.collectAsState()
        
        Scaffold(modifier = Modifier.setupScreenData(this), content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                when (state) {
                    is MainState.Intro    -> IntroState(startClick = {
                        navController.navigate(SETTING)
                    })
                    
                    is MainState.Loading  -> LoadingState()
                    is MainState.Error    -> ErrorState(retryClick = {
                        viewModel.retry()
                    })
                    is MainState.Intercom -> IntercomState()
                }
            }
            
            Buttons(openSettingClick = {
                navController.navigate(SETTING)
            }, openHistoryClick = {
                navController.navigate(HISTORY)
            })
        })
    }
    
    @Composable
    private fun IntroState(
        startClick: () -> Unit,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.align(Alignment.Center),
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
                modifier = Modifier
                    .testTag(MainScreenId.buttonStart)
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 64.dp), onClick = startClick
            ) {
                Text(
                    text = "Start", style = MaterialTheme.typography.button.normal
                )
            }
        }
    }
    
    @Composable
    private fun LoadingState() {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                
                RoundedLinearProgressIndicator(
                    modifier = Modifier
                        .width(256.dp)
                        .padding(4.dp),
                    color = MaterialTheme.colorScheme.primary,
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer
                )
                
                Text(
                    "Please, wait", modifier = Modifier
                        .padding(all = 4.dp)
                        .padding(top = 8.dp)
                )
            }
        }
    }
    
    @Composable
    private fun ErrorState(retryClick: () -> Unit) {
        Column {
            Text("Whoops...", fontSize = 32.sp, fontWeight = FontWeight.ExtraBold)
            Button(modifier = Modifier.testTag(MainScreenId.buttonRetry), onClick = retryClick) {
                Text("Retry")
            }
        }
    }
    
    @Composable
    private fun IntercomState() {
        
    }
    
    @Composable
    private fun Buttons(openSettingClick: () -> Unit, openHistoryClick: () -> Unit) {
        Box(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    modifier = Modifier.testTag(MainScreenId.buttonSettings),
                    onClick = openSettingClick,
                ) {
                    Text(stringResource(id = R.string.main_screen_intro_button))
                }
                
                Button(
                    modifier = Modifier.testTag(MainScreenId.buttonHistory),
                    onClick = openHistoryClick,
                ) {
                    Text("Go to history")
                }
            }
        }
        
        
    }
    
    @Composable
    private fun RoundedLinearProgressIndicator(
        modifier: Modifier = Modifier,
        height: Dp = 8.dp,
        color: Color = MaterialTheme.colorScheme.primary,
        backgroundColor: Color = color.copy(
            alpha = ProgressIndicatorDefaults.linearColor.alpha
        ),
    ) {
        val infiniteTransition = rememberInfiniteTransition(label = "")
        val animatedColor by infiniteTransition.animateColor(
            initialValue = color,
            targetValue = color.copy(alpha = .5f),
            animationSpec = infiniteRepeatable(
                animation = tween(768, easing = LinearEasing), repeatMode = RepeatMode.Reverse
            ),
            label = ""
        )
        
        val animTranslationX by infiniteTransition.animateFloat(
            initialValue = 0f, targetValue = .75f, animationSpec = infiniteRepeatable(
                animation = tween(1000, easing = LinearEasing), repeatMode = RepeatMode.Reverse
            ), label = ""
        )
        
        Box(
            modifier.fillMaxWidth()
        ) {
            Row(
                Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .height(height)
                    .padding(start = 32.dp, end = 32.dp)
                    .background(backgroundColor, CircleShape)
            ) {
                BoxWithConstraints {
                    Box(
                        Modifier
                            .graphicsLayer {
                                translationX = (maxWidth * animTranslationX).toPx()
                            }
                            .fillMaxHeight()
                            .clip(CircleShape)
                            .width(maxWidth * .25f)
                            .background(animatedColor, CircleShape))
                }
            }
        }
    }
    
    @Composable
    fun ButtonsPreview() {
        Buttons({}, {})
    }
}

@Preview(showBackground = true)
@Composable
fun RenderPreview() {
    SmartIntercomTheme {
        Row {
            MainScreen.ButtonsPreview()
        }
    }
}
