package ru.samsung.smartintercom.ui.screen.setting

import android.content.Context
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.samsung.smartintercom.data.intercom.IntercomInfoDTO
import ru.samsung.smartintercom.data.intercom.IntercomInfoRepositoryImpl

class SettingViewModel(val intercomInfoRepositoryImpl: IntercomInfoRepositoryImpl) : ViewModel() {
    private val _intercomInfo = MutableStateFlow(IntercomInfoDTO("", ""))
    val intercomInfo = _intercomInfo.asStateFlow()
    
    fun loadIntercomInfo(context: Context) {
        _intercomInfo.value = intercomInfoRepositoryImpl.getInfo(context)
    }
    
    fun loadToStorage(context: Context) {
        intercomInfoRepositoryImpl.saveInfo(context, intercomInfo.value)
    }
    
    fun changeHouse(house: String) {
        _intercomInfo.update {
            it.copy(house = house)
        }
    }
    
    fun changeFlat(flat: String) {
        _intercomInfo.update {
            it.copy(flat = flat)
        }
    }
}