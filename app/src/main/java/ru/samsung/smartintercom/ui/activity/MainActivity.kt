package ru.samsung.smartintercom.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.koinViewModel
import ru.samsung.smartintercom.ui.nav.Navigation
import ru.samsung.smartintercom.ui.nav.Screen
import ru.samsung.smartintercom.ui.nav.navigate
import ru.samsung.smartintercom.ui.screen.call.CallScreen
import ru.samsung.smartintercom.ui.screen.main.MainScreen
import ru.samsung.smartintercom.ui.theme.SmartIntercomTheme
import ru.samsung.smartintercom.utils.collectAsEffect

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartIntercomTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    Navigation(navController = navController)
                    val viewModel: MainActivityViewModel = koinViewModel()
                    viewModel.openCallScreen.collectAsEffect {
                        navController.navigate(Screen.CALL)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SmartIntercomTheme {
        MainScreen.Render(rememberNavController())
    }
}
