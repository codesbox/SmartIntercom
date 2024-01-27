package ru.samsung.smartintercom.ui.screen.callhistory

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
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
            LazyColumn(
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
    
    @Composable
    fun CallInfoCard(callInfo: CallInfo) {
        Card {
            Row {
                Text(text = callInfo.callTime.convertToString())
                Text(
                    text = when (callInfo.doorState) {
                        OPEN  -> "Opened"
                        CLOSE -> "Closed"
                    }
                )
            }
        }
    }
    
    private fun CallTime.convertToString() = "$hours:$minutes $day.$month.$year"
}