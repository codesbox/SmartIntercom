package ru.samsung.smartintercom.ui.screen.main

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextAlign.Companion
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.samsung.smartintercom.R.string
import ru.samsung.smartintercom.core.MainScreenId
import ru.samsung.smartintercom.ui.nav.Screen.HISTORY
import ru.samsung.smartintercom.ui.nav.Screen.SETTING
import ru.samsung.smartintercom.ui.nav.navigate
import ru.samsung.smartintercom.ui.screen.ScreenBaseData
import ru.samsung.smartintercom.ui.screen.main.MainState.*
import ru.samsung.smartintercom.ui.theme.button
import ru.samsung.smartintercom.utils.setupScreenData

object MainScreen : ScreenBaseData {
    override val name = MainScreenId.screenId
    
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Render(navController: NavController) {
        
        val viewModel: MainViewModel = koinViewModel()
        val state by viewModel.uiState.collectAsState()
        
        LaunchedEffect(key1 = Unit, block = {
            viewModel.loadFirst()
        })
        
        Scaffold(modifier = Modifier.setupScreenData(this), content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                when (state) {
                    is Intro    -> IntroState(
                        onLaunch = {
                            viewModel.loadIntercom()
                        },
                        startClick = {
                            viewModel.retry()
                            navController.navigate(SETTING)
                        },
                    )
                    
                    is Loading  -> LoadingState(onLaunch = {
                        viewModel.loadIntercom()
                    })
                    
                    is Error    -> ErrorState(retryClick = viewModel::retry)
                    is Intercom -> IntercomState(
                        mainState = state as Intercom,
                        takePhotoClick = viewModel::takePhoto,
                        retryClick = viewModel::retry,
                    )
                }
            }
            
            Buttons(
                openSettingClick = { navController.navigate(SETTING) },
                openHistoryClick = { navController.navigate(HISTORY) },
            )
        })
    }
    
    @Composable
    private fun IntroState(
        onLaunch: () -> Unit,
        startClick: () -> Unit,
    ) {
        LaunchedEffect(key1 = Unit, block = {
            onLaunch()
        })
        
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
                .background(MaterialTheme.colorScheme.surface),
        ) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = stringResource(string.main_screen_intro_title),
                    style = MaterialTheme.typography.displayLarge,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center,
                )
                Text(
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = stringResource(string.main_screen_intro_text),
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center,
                    maxLines = 5,
                )
                Button(
                    modifier = Modifier
                        .testTag(MainScreenId.buttonStart)
                        .fillMaxWidth(0.75f)
                        .padding(vertical = 16.dp),
                    onClick = startClick,
                ) {
                    Text(
                        text = stringResource(string.start),
                        style = MaterialTheme.typography.button.large,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
    
    @Composable
    private fun LoadingState(onLaunch: () -> Unit) {
        
        LaunchedEffect(key1 = Unit, block = {
            onLaunch()
        })
        
        Box(modifier = Modifier.fillMaxSize()) {
            RoundedLinearProgressIndicator(
                modifier = Modifier
                    .width(256.dp)
                    .padding(4.dp)
                    .align(Alignment.Center),
            )
        }
    }
    
    @Composable
    private fun ErrorState(retryClick: () -> Unit) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround,
            ) {
                Text(
                    text = "Error: incorrect data",
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Button(
                    modifier = Modifier
                        .testTag(MainScreenId.buttonRetry)
                        .padding(vertical = 8.dp),
                    onClick = retryClick
                ) {
                    Text(
                        text = "Retry",
                        style = MaterialTheme.typography.button.large,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
    
    @Composable
    private fun IntercomState(
        mainState: Intercom,
        takePhotoClick: () -> Unit,
        retryClick: () -> Unit,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = mainState.model.model,
                    modifier = Modifier
                        .testTag(MainScreenId.textIntercomModel)
                        .padding(vertical = 8.dp, horizontal = 8.dp)
                        .align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = Companion.Center
                )
                
                if (!mainState.firstEntry) {
                    Box(
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .fillMaxWidth()
                            .height(196.dp)
                    ) {
                        when (mainState.image) {
                            null -> CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center)
                            )
                            
                            else -> Image(
                                modifier = Modifier
                                    .testTag(MainScreenId.imageIntercom)
                                    .padding(vertical = 8.dp)
                                    .height(196.dp)
                                    .align(Alignment.Center),
                                painter = BitmapPainter(mainState.image),
                                contentDescription = stringResource(string.image_description),
                                contentScale = ContentScale.Crop,
                            )
                        }
                    }
                }
                
                Button(
                    modifier = Modifier
                        .testTag(MainScreenId.buttonTakePhoto)
                        .fillMaxWidth(0.75f)
                        .padding(vertical = 8.dp),
                    onClick = takePhotoClick,
                ) {
                    Text(
                        text = stringResource(string.take_photo),
                        style = MaterialTheme.typography.button.normal,
                        textAlign = TextAlign.Center,
                    )
                }
                
                Button(
                    modifier = Modifier
                        .testTag(MainScreenId.buttonRetry)
                        .fillMaxWidth(0.75f)
                        .padding(vertical = 8.dp),
                    onClick = retryClick,
                ) {
                    Text(
                        text = stringResource(string.retry),
                        style = MaterialTheme.typography.button.normal,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
    
    @Composable
    private fun Buttons(openSettingClick: () -> Unit, openHistoryClick: () -> Unit) {
        Box(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    modifier = Modifier.testTag(MainScreenId.buttonSettings),
                    onClick = openSettingClick,
                ) {
                    Text(
                        text = stringResource(id = string.main_screen_intro_button),
                        textAlign = TextAlign.Center,
                    )
                }
                
                Button(
                    modifier = Modifier.testTag(MainScreenId.buttonHistory),
                    onClick = openHistoryClick,
                ) {
                    Text(text = stringResource(string.go_to_history), textAlign = Companion.Center)
                }
            }
        }
        
        
    }
    
    @Composable
    private fun RoundedLinearProgressIndicator(
        modifier: Modifier = Modifier,
        height: Dp = 8.dp,
        color: Color = MaterialTheme.colorScheme.primary,
        backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
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
                    Box(Modifier
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
}
