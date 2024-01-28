package ru.samsung.smartintercom.ui.screen.call

import android.icu.util.Calendar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.samsung.smartintercom.domain.callHistory.SaveCallInfoUseCase
import ru.samsung.smartintercom.domain.callHistory.models.CallInfo
import ru.samsung.smartintercom.domain.callHistory.models.CallTime
import ru.samsung.smartintercom.domain.callHistory.models.DoorState.CLOSE
import ru.samsung.smartintercom.domain.callHistory.models.DoorState.OPEN
import ru.samsung.smartintercom.domain.callRespond.CallRespondUseCase
import ru.samsung.smartintercom.utils.MutablePublishFlow

class CallViewModel(
    private val saveCallInfoUseCase: SaveCallInfoUseCase,
    private val callRespondUseCase: CallRespondUseCase
) : ViewModel() {
    val goBackOrOpenMainScreen: SharedFlow<Unit> get() = _goBackOrOpenMainScreen.asSharedFlow()
    private val _goBackOrOpenMainScreen = MutablePublishFlow<Unit>()
    
    fun clickOpen() {
        viewModelScope.launch {
            val calendar = Calendar.getInstance()
            saveCallInfoUseCase.execute(CallInfo(getTime(calendar), OPEN))
            callRespondUseCase.execute(OPEN)
            _goBackOrOpenMainScreen.emit(Unit)
        }
    }
    
    fun clickDecline() {
        viewModelScope.launch {
            val calendar = Calendar.getInstance()
            saveCallInfoUseCase.execute(CallInfo(getTime(calendar), CLOSE))
            callRespondUseCase.execute(CLOSE)
            _goBackOrOpenMainScreen.emit(Unit)
        }
    }
}

private fun getTime(calendar: Calendar): CallTime {
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH) + 1
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)
    
    return CallTime(
        year = year.toString(),
        month = when (month.toString().length) {
            1    -> "0$month"
            else -> month.toString()
        },
        day = when (day.toString().length) {
            1    -> "0$month"
            else -> month.toString()
        },
        hours = when (hour.toString().length) {
            1    -> "0$hour"
            else -> hour.toString()
        },
        minutes = when (minute.toString().length) {
            1    -> "0$minute"
            else -> minute.toString()
        },
    )
}
