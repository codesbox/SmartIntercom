package ru.samsung.smartintercom.ui.screen.callhistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.samsung.smartintercom.domain.callHistory.GetCallHistoryUseCase
import ru.samsung.smartintercom.domain.callHistory.models.CallInfo

class CallHistoryViewModel(private val getCallHistoryUseCase: GetCallHistoryUseCase) : ViewModel() {

    private val _calls = MutableStateFlow<List<CallInfo>>(listOf())
    val calls: StateFlow<List<CallInfo>> = _calls.asStateFlow()
    
    fun getHistory(){
        viewModelScope.launch {
            getCallHistoryUseCase.execute().collectLatest {
                _calls.emit(it)
            }
        }
    }

}