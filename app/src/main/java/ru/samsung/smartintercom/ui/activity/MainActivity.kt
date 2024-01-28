package ru.samsung.smartintercom.ui.activity

import android.Manifest.permission
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.rememberNavController
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.koinViewModel
import ru.samsung.smartintercom.ui.nav.Navigation
import ru.samsung.smartintercom.ui.nav.Screen
import ru.samsung.smartintercom.ui.nav.navigate
import ru.samsung.smartintercom.ui.notification.NotificationHelper
import ru.samsung.smartintercom.ui.theme.SmartIntercomTheme
import ru.samsung.smartintercom.utils.collectAsEffect

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    permission.POST_NOTIFICATIONS
                ), 0
            )
        }
        
        setContent {
            SmartIntercomTheme { // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    Navigation(navController = navController)
                    val viewModel: MainActivityViewModel = koinViewModel()
                    viewModel.openCallScreen.collectAsEffect {
                        viewModel.sendNullToSharedFlow()
                        navController.navigate(Screen.CALL)
                    }
                    viewModel.sendDataToSharedFlow()
                }
            }
        }
    }
}
