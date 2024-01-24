package ru.samsung.smartintercom.ui.screen.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.samsung.smartintercom.ui.screen.main.MainState.Intercom

class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<MainState>(MainState.Intro)
    val uiState: StateFlow<MainState> = _uiState.asStateFlow()
    
    fun firstLaunch() {
        _uiState.update {
            MainState.Loading
        }
        
        // TODO: Сделать обработку, первый ли сейчас запуск, если да - IntroState, если нет - LoadingState
    }
    
    fun takePhoto() {
        _uiState.update {
            val state = it as Intercom
            Intercom(model = state.model, firstEntry = false, image = null)
        }
    }
    
    fun loadIntercom() {
        _uiState.update { MainState.Loading }
        
        _uiState.update {
            Intercom(
                model = "TEST INTERCOM", firstEntry = true, image = null
            )
        } //            TODO: Запрос на сервер по info, должен выдать информацию о модели домофона, вместо TEST INTERCOM вставить полученные данные. Если есть какая-то ошибка, обновить стейт на Error
    }
    
    fun retry() {
        _uiState.update { MainState.Loading }
    }
}
