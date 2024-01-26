package ru.samsung.smartintercom.domain.auth

import ru.samsung.smartintercom.domain.auth.model.AuthEntity

class GetAuthDataUseCase(private val authDataSource: AuthDataSource) {
    fun execute(): AuthEntity {
        return authDataSource.getAuthData()

    }
}

