package ru.samsung.smartintercom.ui.screen.call

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.rememberNavController
import ru.samsung.smartintercom.R
import ru.samsung.smartintercom.core.CallScreenId
import ru.samsung.smartintercom.ui.nav.Screen
import ru.samsung.smartintercom.ui.nav.navigate
import ru.samsung.smartintercom.ui.screen.ScreenBaseData
import ru.samsung.smartintercom.ui.screen.call.di.CallScreenComponent
import ru.samsung.smartintercom.ui.theme.SmartIntercomTheme
import ru.samsung.smartintercom.ui.theme.button
import ru.samsung.smartintercom.utils.collectAsEffect
import ru.samsung.smartintercom.utils.daggerViewModel
import ru.samsung.smartintercom.utils.setupScreenData

/**
 * Экран вызова
 *
 * ВНИМАНИЕ!
 * Данный экран приведён для примера.
 * Вы можете его модифицировать любыми удобными способами и дорабатывать его.
 */
object CallScreen : ScreenBaseData {
    override val name = CallScreenId.screenId

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Render(navController: NavController) {
        val component = CallScreenComponent.build()
        val viewModel = daggerViewModel { component.viewModel }
        viewModel.goBackOrOpenMainScreen.collectAsEffect {
            if (navController.graph.hierarchy.count() > 0) {
                navController.popBackStack()
            } else {
                navController.navigate(Screen.MAIN)
            }
        }
        Scaffold(
            modifier = Modifier.setupScreenData(CallScreen),
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .padding(paddingValues = paddingValues)
                        .padding(16.dp),
                ) {
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .wrapContentHeight(align = Alignment.CenterVertically),
                        text = stringResource(R.string.call_screen_title),
                        style = MaterialTheme.typography.displayLarge,
                        textAlign = TextAlign.Center,
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        Button(
                            id = CallScreenId.buttonOpen,
                            text = stringResource(R.string.open_intercom),
                            color = colorResource(R.color.call_open),
                            onClick = viewModel::clickOpen,
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Button(
                            id = CallScreenId.buttonClose,
                            text = stringResource(R.string.decline_intercom),
                            color = colorResource(R.color.call_close),
                            onClick = viewModel::clickDecline,
                        )
                    }
                }
            }
        )
    }

    @Composable
    fun RowScope.Button(
        id: String,
        text: String,
        color: Color,
        onClick: () -> Unit
    ) {
        Button(
            modifier = Modifier
                .testTag(id)
                .weight(1.0f, true),
            colors = ButtonDefaults.buttonColors(containerColor = color),
            onClick = onClick,
        ) {
            Text(
                style = MaterialTheme.typography.button.large,
                text = text,
            )
        }

    }
}

@Preview(showBackground = true, device = "spec:width=1080px,height=1920px,dpi=320")
@Composable
fun CallScreenPreview() {
    SmartIntercomTheme {
        CallScreen.Render(rememberNavController())
    }
}
