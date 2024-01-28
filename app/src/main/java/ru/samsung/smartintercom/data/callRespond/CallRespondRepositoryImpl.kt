package ru.samsung.smartintercom.data.callRespond

import kotlinx.coroutines.flow.Flow
import ru.samsung.smartintercom.domain.auth.GetAuthDataUseCase
import ru.samsung.smartintercom.domain.callHistory.models.DoorState
import ru.samsung.smartintercom.domain.callRespond.CallRespondDataSource
import ru.samsung.smartintercom.domain.callRespond.CallRespondRepository
import ru.samsung.smartintercom.domain.callRespond.models.CallRespondStatus

class CallRespondRepositoryImpl(private val callRespondDataSource: CallRespondDataSource): CallRespondRepository {
    override fun respond(doorState: DoorState): Flow<CallRespondStatus> {
        return callRespondDataSource.respond(doorState)
    }
}