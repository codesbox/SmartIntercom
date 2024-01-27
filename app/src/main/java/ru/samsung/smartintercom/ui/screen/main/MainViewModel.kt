package ru.samsung.smartintercom.ui.screen.main

import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.samsung.smartintercom.domain.auth.GetAuthDataUseCase
import ru.samsung.smartintercom.domain.intercom.GetImageUseCase
import ru.samsung.smartintercom.domain.intercom.GetModelUseCase
import ru.samsung.smartintercom.ui.screen.main.MainState.*

class MainViewModel(
    private val getAuthDataUseCase: GetAuthDataUseCase,
    private val getImageUseCase: GetImageUseCase,
    private val getModelUseCase: GetModelUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<MainState>(Loading)
    val uiState: StateFlow<MainState> = _uiState.asStateFlow()
    
    init {
        loadIntercom()
    }
    
    fun loadFirst() {
        viewModelScope.launch {
            val entity = getAuthDataUseCase.execute()
            if ((entity.house == "") or (entity.room == "")) {
                _uiState.emit(Intro)
            } else {
                getModelUseCase.execute().collectLatest {
                    if (it == null) {
                        _uiState.emit(Error)
                    } else {
                        _uiState.emit(Intercom(model = it, firstEntry = true, image = null))
                    }
                }
            }
        }
    }
    
    fun takePhoto() {
        viewModelScope.launch {
            getImageUseCase.execute().collectLatest { bitmap ->
                if (bitmap == null) {
                    _uiState.update { Error }
                    return@collectLatest
                }
                _uiState.update {
                    val state = it as Intercom
                    Intercom(
                        model = state.model, firstEntry = false, image = bitmap.asImageBitmap()
                    )
                }
            }
        }
    }
    
    fun loadIntercom() {
        viewModelScope.launch {
            val entity = getAuthDataUseCase.execute()
            if ((entity.house == "") or (entity.room == "")) {
                _uiState.emit(Intro)
            } else {
                getModelUseCase.execute().collectLatest { model ->
                    when (model) {
                        null -> _uiState.emit(Error)
                        else -> _uiState.emit(
                            Intercom(
                                model = model, firstEntry = true, image = null
                            )
                        )
                    }
                }
            }
        }
    }
    
    fun retry() {
        _uiState.update { Loading }
    }
}
