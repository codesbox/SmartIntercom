package ru.samsung.smartintercom.domain.auth

import kotlinx.coroutines.flow.Flow
import ru.samsung.smartintercom.domain.auth.model.AuthEntity

interface AuthRepository {
    /**
     * Данное значение передаёт при каждом изменении новое значение сущности авторизации
     * в случае, если пользователь неавторизован - необходимо передать null
     */
    val authData: Flow<AuthEntity?>
}
