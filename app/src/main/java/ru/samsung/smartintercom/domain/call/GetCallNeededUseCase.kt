package ru.samsung.smartintercom.domain.call

import kotlinx.coroutines.flow.Flow

class GetCallNeededUseCase(
    private val callRepository: CallRepository
) {
    fun execute(): Flow<Unit> = callRepository.intercomCallStart
}
