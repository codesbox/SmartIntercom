package ru.samsung.smartintercom.domain.call

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCallNeededUseCase @Inject constructor(
    private val callRepository: CallRepository
) {
    fun execute(): Flow<Unit> = callRepository.intercomCallStart
}
