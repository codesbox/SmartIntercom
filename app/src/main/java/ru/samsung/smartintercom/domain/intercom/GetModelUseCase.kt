package ru.samsung.smartintercom.domain.intercom

import kotlinx.coroutines.flow.Flow

class GetModelUseCase(private val intercomRepository: IntercomRepository){
    fun execute(): Flow<IntercomModel?> = intercomRepository.getModel()
}