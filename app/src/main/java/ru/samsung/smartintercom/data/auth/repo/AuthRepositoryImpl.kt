package ru.samsung.smartintercom.data.auth.repo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.samsung.smartintercom.domain.auth.AuthRepository
import ru.samsung.smartintercom.domain.auth.model.AuthEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor() : AuthRepository {

    // TODO: Необходимо переопределить праивильно функцию
    override val authData: Flow<AuthEntity?> get() = flow { emit(null) }
}
