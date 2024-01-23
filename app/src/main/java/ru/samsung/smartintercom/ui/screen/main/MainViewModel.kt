package ru.samsung.smartintercom.ui.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.samsung.smartintercom.domain.auth.AuthRepository
import ru.samsung.smartintercom.domain.auth.model.AuthEntity

class MainViewModel(private val authRepository: AuthRepository) : ViewModel() {
    
    private val _authEntity = MutableStateFlow<AuthEntity?>(null)
    
    val uiState: StateFlow<MainState> get() = _uiState.asStateFlow()
    private val _uiState = MutableStateFlow<MainState>(MainState.Intro)
    
    init {
        viewModelScope.launch {
            _uiState.emit(MainState.Loading)
        }
    }
    
    fun loadIntercom() {
        viewModelScope.launch {
            authRepository.authData.collectLatest {
                _authEntity.emit(it)
            }
        }
    }
    
    fun retry() {
        viewModelScope.launch {
            _uiState.emit(MainState.Loading)
        }
    }
}
