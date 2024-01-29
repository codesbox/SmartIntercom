package ru.samsung.smartintercom.ui.screen.setting

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.samsung.smartintercom.domain.auth.GetAuthDataUseCase
import ru.samsung.smartintercom.domain.auth.SendNullToSharedFlowUseCase
import ru.samsung.smartintercom.domain.auth.SetAuthDataUseCase
import ru.samsung.smartintercom.domain.auth.model.AuthEntity

class SettingViewModel(
    private val setAuthDataUseCase: SetAuthDataUseCase,
    private val getAuthDataUseCase: GetAuthDataUseCase,
    private val sendNullToSharedFlowUseCase: SendNullToSharedFlowUseCase,
) : ViewModel() {
    private val _intercomInfo = MutableStateFlow(AuthEntity("", ""))
    val intercomInfo = _intercomInfo.asStateFlow()
    
    val previousInfo: AuthEntity by lazy {
        getAuthDataUseCase.execute()
    }
    
    fun loadIntercomInfo() {
        _intercomInfo.value = getAuthDataUseCase.execute()
    }
    
    suspend fun loadToStorage() {
        sendNullToSharedFlowUseCase.execute()
        setAuthDataUseCase.execute(intercomInfo.value)
    }
    
    fun changeHouse(house: String) {
        _intercomInfo.update {
            it.copy(house = house)
        }
    }
    
    fun changeFlat(flat: String) {
        _intercomInfo.update {
            it.copy(room = flat)
        }
    }
}