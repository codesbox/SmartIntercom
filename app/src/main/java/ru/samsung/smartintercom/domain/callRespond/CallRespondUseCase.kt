package ru.samsung.smartintercom.domain.callRespond

import kotlinx.coroutines.flow.Flow
import ru.samsung.smartintercom.domain.auth.GetAuthDataUseCase
import ru.samsung.smartintercom.domain.callHistory.models.DoorState
import ru.samsung.smartintercom.domain.callRespond.models.CallRespondStatus

class CallRespondUseCase(private val callRespondRepository: CallRespondRepository) {

    fun execute(doorState: DoorState): Flow<CallRespondStatus> {
        return callRespondRepository.respond(doorState)
    }

}