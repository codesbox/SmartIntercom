package ru.samsung.smartintercom.domain.callRespond

import kotlinx.coroutines.flow.Flow
import ru.samsung.smartintercom.domain.auth.GetAuthDataUseCase
import ru.samsung.smartintercom.domain.callHistory.models.DoorState
import ru.samsung.smartintercom.domain.callRespond.models.CallRespondStatus

interface CallRespondRepository {
    fun respond(doorState: DoorState): Flow<CallRespondStatus>
}