package ru.samsung.smartintercom.domain.auth

import kotlinx.coroutines.flow.Flow
import ru.samsung.smartintercom.domain.auth.model.AuthEntity

interface AuthDataSource {
    fun getFlow(): Flow<AuthEntity?>
    suspend fun setAuthData(authEntity: AuthEntity)
    suspend fun sendDataToSharedFlow()
    fun getAuthData(): AuthEntity
    suspend fun sendNullToSharedFlow()

}