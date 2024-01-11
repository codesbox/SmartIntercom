package ru.samsung.smartintercom.ui.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val uiState: StateFlow<MainState> get() = _uiState.asStateFlow()
    private val _uiState = MutableStateFlow<MainState>(MainState.Loading)

    init {
        viewModelScope.launch {
            _uiState.emit(MainState.Intro)
        }
    }
}
