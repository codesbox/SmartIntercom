package ru.samsung.smartintercom.domain.call

import kotlinx.coroutines.flow.Flow

interface CallRepository {
    val intercomCallStart: Flow<Unit>
}
