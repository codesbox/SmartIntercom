package ru.samsung.smartintercom.ui.activity

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.samsung.smartintercom.domain.auth.AuthDataSource
import ru.samsung.smartintercom.domain.call.GetCallNeededUseCase
import ru.samsung.smartintercom.utils.MutablePublishFlow

class MainActivityViewModel(
    private val getCallNeededUseCase: GetCallNeededUseCase,
    private val authDataSource: AuthDataSource
) : ViewModel() {
    val openCallScreen: SharedFlow<Unit> get() = _openCallScreen.asSharedFlow()
    private val _openCallScreen = MutablePublishFlow<Unit>()
    fun sendDataToSharedFlow(){
        viewModelScope.launch {
            authDataSource.sendDataToSharedFlow()
        }

    }

    init {
        viewModelScope.launch {
            getCallNeededUseCase.execute().collect {
                _openCallScreen.emit(Unit)
            }

        }
    }
}
