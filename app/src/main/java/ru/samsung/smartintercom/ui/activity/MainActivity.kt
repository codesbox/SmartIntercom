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
import ru.samsung.smartintercom.ui.activity.di.MainActivityComponent
import ru.samsung.smartintercom.ui.nav.Navigation
import ru.samsung.smartintercom.ui.nav.Screen
import ru.samsung.smartintercom.ui.nav.navigate
import ru.samsung.smartintercom.ui.screen.main.MainScreen
import ru.samsung.smartintercom.ui.theme.SmartIntercomTheme
import ru.samsung.smartintercom.utils.collectAsEffect
import ru.samsung.smartintercom.utils.daggerViewModel

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
                    val component = MainActivityComponent.build()
                    val viewModel = daggerViewModel { component.viewModel }
                    viewModel.openCallScreen.collectAsEffect {
                        navController.navigate(Screen.CALL)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, device = "spec:width=1080px,height=1920px,dpi=320")
@Composable
fun GreetingPreview() {
    SmartIntercomTheme {
        MainScreen.Render(rememberNavController())
    }
}
