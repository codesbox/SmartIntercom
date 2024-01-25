package ru.samsung.smartintercom.domain.callHistory

import kotlinx.coroutines.flow.Flow
import ru.samsung.smartintercom.domain.callHistory.models.CallInfo

class GetCallHistoryUseCase(private val callHistoryRepository: CallHistoryRepository) {

    fun execute(): Flow<List<CallInfo>> = callHistoryRepository.getCallHistory()
}