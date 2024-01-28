package ru.samsung.smartintercom.domain.call

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class GetCallNeededUseCase(
    private val callRepository: CallRepository
) {
    fun execute(): Flow<Unit> = callRepository.intercomCallStart.flowOn(Dispatchers.IO)
}
