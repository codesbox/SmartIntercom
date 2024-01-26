package ru.samsung.smartintercom.domain.auth

import ru.samsung.smartintercom.domain.auth.model.AuthEntity

class SetAuthDataUseCase(private val authDataSource: AuthDataSource) {
    suspend fun execute(authEntity: AuthEntity){
        authDataSource.setAuthData(authEntity)

    }
}