package ru.samsung.smartintercom.ui.screen.call

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.samsung.smartintercom.utils.MutablePublishFlow
import javax.inject.Inject

class CallViewModel @Inject constructor() : ViewModel() {
    val goBackOrOpenMainScreen: SharedFlow<Unit> get() = _goBackOrOpenMainScreen.asSharedFlow()
    private val _goBackOrOpenMainScreen = MutablePublishFlow<Unit>()
    fun clickOpen() {
        viewModelScope.launch {
            // TODO: необходимо добавить обработку нажатия на кнопку открытия
            _goBackOrOpenMainScreen.emit(Unit)
        }
    }

    fun clickDecline() {
        viewModelScope.launch {
            // TODO: необходимо добавить обработку нажатия на кнопку закрытия
            _goBackOrOpenMainScreen.emit(Unit)
        }
    }
}
