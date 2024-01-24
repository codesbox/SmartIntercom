package ru.samsung.smartintercom.data.auth.repo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.samsung.smartintercom.domain.auth.AuthDataSource
import ru.samsung.smartintercom.domain.auth.AuthRepository
import ru.samsung.smartintercom.domain.auth.model.AuthEntity


class AuthRepositoryImpl(private val authDataSource: AuthDataSource) : AuthRepository {

    override val authData: Flow<AuthEntity?> get() = authDataSource.getFlow()
}
