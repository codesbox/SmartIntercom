package ru.samsung.smartintercom.ui.screen.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.samsung.smartintercom.domain.auth.AuthDataSource
import ru.samsung.smartintercom.domain.auth.AuthRepository
import ru.samsung.smartintercom.domain.auth.GetAuthDataUseCase
import ru.samsung.smartintercom.ui.screen.main.MainState.*

class MainViewModel(private val getAuthDataUseCase: GetAuthDataUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow<MainState>(Loading)
    val uiState: StateFlow<MainState> = _uiState.asStateFlow()
    
    fun takePhoto() {
        _uiState.update {
            val state = it as Intercom
            Intercom(model = state.model, firstEntry = false, image = null)
        }
    }
    
    fun loadIntercom() {
        val entity = getAuthDataUseCase.execute()
        if((entity.house == "") or (entity.room == "")){
            _uiState.update { Intro }
        }
        else{
            // TODO: полуение модели
            _uiState.update { Intercom(firstEntry = true) }
        }
    }
    
    fun retry() {
        _uiState.update { Loading }
    }
}
