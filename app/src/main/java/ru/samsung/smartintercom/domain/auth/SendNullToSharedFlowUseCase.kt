package ru.samsung.smartintercom.domain.auth

class SendNullToSharedFlowUseCase(private val authDataSource: AuthDataSource) {
    suspend fun execute(){
        authDataSource.sendNullToSharedFlow()
    }

}