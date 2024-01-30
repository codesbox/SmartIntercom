package ru.samsung.smartintercom.ui.screen.callhistory

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.samsung.smartintercom.R
import ru.samsung.smartintercom.R.string
import ru.samsung.smartintercom.core.CallHistoryId
import ru.samsung.smartintercom.domain.callHistory.models.CallInfo
import ru.samsung.smartintercom.domain.callHistory.models.CallTime
import ru.samsung.smartintercom.domain.callHistory.models.DoorState.CLOSE
import ru.samsung.smartintercom.domain.callHistory.models.DoorState.OPEN
import ru.samsung.smartintercom.ui.screen.ScreenBaseData
import ru.samsung.smartintercom.utils.setupScreenData

object CallHistoryScreen : ScreenBaseData {
    override val name = CallHistoryId.screenId

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Render(navController: NavController) {
        val viewModel = koinViewModel<CallHistoryViewModel>()
        val calls by viewModel.calls.collectAsState()

        LaunchedEffect(key1 = Unit, block = {
            viewModel.getHistory()
        })


        Scaffold(modifier = Modifier.setupScreenData(this)) { paddingValues ->
            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {
                IconButton(modifier = Modifier
                    .align(Alignment.Start)
                    .padding(8.dp) ,onClick = { navController.popBackStack() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.back),
                        contentDescription = stringResource(string.go_back)
                    )
                }
                when (calls.isEmpty()) {
                    true -> Box(modifier = Modifier.fillMaxSize()) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = stringResource(string.no_calls_yet),
                            style = MaterialTheme.typography.displaySmall,
                            textAlign = TextAlign.Center,
                        )
                    }

                    false -> LazyColumn(
                        modifier = Modifier
                            .testTag(CallHistoryId.recycler)
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        items(calls) {
                            CallInfoCard(callInfo = it)
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun CallInfoCard(callInfo: CallInfo) {
        Card(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 8.dp, horizontal = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.testTag(CallHistoryId.textDate),
                    text = callInfo.callTime.convertToString(),
                    style = MaterialTheme.typography.headlineLarge
                )
                Text(
                    modifier = Modifier.testTag(CallHistoryId.textStatus),
                    text = when (callInfo.doorState) {
                        OPEN -> stringResource(string.opened)
                        CLOSE -> stringResource(string.closed)
                    },
                    style = MaterialTheme.typography.headlineLarge
                )
            }
        }
    }

    private fun CallTime.convertToString() = "$hours:$minutes $day.$month.$year"
}