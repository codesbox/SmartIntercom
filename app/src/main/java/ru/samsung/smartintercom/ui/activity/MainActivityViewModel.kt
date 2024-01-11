package ru.samsung.smartintercom.ui.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.samsung.smartintercom.domain.call.GetCallNeededUseCase
import ru.samsung.smartintercom.utils.MutablePublishFlow

class MainActivityViewModel(
    private val getCallNeededUseCase: GetCallNeededUseCase
) : ViewModel() {
    val openCallScreen: SharedFlow<Unit> get() = _openCallScreen.asSharedFlow()
    private val _openCallScreen = MutablePublishFlow<Unit>()

    init {
        viewModelScope.launch {
            getCallNeededUseCase.execute().collect {
                _openCallScreen.emit(Unit)
            }
        }
    }
}
