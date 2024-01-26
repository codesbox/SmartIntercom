package ru.samsung.smartintercom.domain.auth

import ru.samsung.smartintercom.domain.auth.model.AuthEntity

class SendDataToSharedFlowUseCase(private val authDataSource: AuthDataSource) {
    suspend fun execute(){
        authDataSource.sendDataToSharedFlow()

    }
}