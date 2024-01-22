package ru.samsung.smartintercom.ui.screen.loading

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.samsung.smartintercom.data.intercom.IntercomInfoDTO
import ru.samsung.smartintercom.data.intercom.IntercomInfoRepositoryImpl

sealed class LoadingState{
    data class Received(val info: IntercomInfoDTO) : LoadingState()
    
    data object Error : LoadingState()
    
    data object Loading : LoadingState()
    
    data object Nothing : LoadingState()
}

class LoadingViewModel(private val intercomInfoRepositoryImpl: IntercomInfoRepositoryImpl) :
    ViewModel() {
    
    private val _loadingState: MutableStateFlow<LoadingState> = MutableStateFlow(LoadingState.Nothing)
    val loadingState = _loadingState.asStateFlow()
    
    fun load(context: Context){
        viewModelScope.launch {
            _loadingState.emit(LoadingState.Loading)
            _loadingState.emit(LoadingState.Received(intercomInfoRepositoryImpl.getInfo(context)))
        }
    }
}